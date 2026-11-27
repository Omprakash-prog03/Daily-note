package com.studentos.domain.logic

import com.studentos.data.entity.*
import com.studentos.domain.model.AttendanceStats
import com.studentos.domain.model.AttendanceStatus
import kotlinx.coroutines.flow.Flow

class AttendanceCalculator {

    fun calculateStats(subject: Subject, presentCount: Int, conductedCount: Int): AttendanceStats {
        val percentage = if (conductedCount == 0) 100.0 else (presentCount.toDouble() / conductedCount) * 100.0
        
        val status = when {
            percentage >= subject.attendanceReq -> AttendanceStatus.SAFE
            percentage >= subject.attendanceReq - 10.0 -> AttendanceStatus.WARNING
            else -> AttendanceStatus.CRITICAL
        }

        return AttendanceStats(subject, presentCount, conductedCount, percentage, status)
    }

    fun predictSafeAbsences(subject: Subject, presentCount: Int, conductedCount: Int): Int {
        if (conductedCount == 0) return 100 // Arbitrary safe start
        val currentPct = (presentCount.toDouble() / conductedCount) * 100.0
        val buffer = currentPct - subject.attendanceReq
        if (buffer <= 0) return 0
        
        // Simple estimation: each absence reduces percentage by (100 / (conductedCount + 1))
        val reductionPerAbsence = 100.0 / (conductedCount + 1.0)
        return (buffer / reductionPerAbsence).toInt()
    }

    fun classesNeededForTarget(subject: Subject, presentCount: Int, conductedCount: Int, targetPct: Double): Int {
        if (percentage(presentCount, conductedCount) >= targetPct) return 0
        
        var currentPresent = presentCount
        var currentConducted = conductedCount
        var needed = 0
        
        while (percentage(currentPresent, currentConducted) < targetPct && needed < 1000) {
            currentPresent++
            currentConducted++
            needed++
        }
        return needed
    }

    private fun percentage(present: Int, conducted: Int): Double {
        return if (conducted == 0) 0.0 else (present.toDouble() / conducted) * 100.0
    }
}

class GpaCalculator {
    // Configurable grading scale: e.g., {"A": 4.0, "B": 3.0, ...}
    fun calculateGpa(results: List<ExaminationResult>, scale: Map<String, Double>): Double {
        if (results.isEmpty()) return 0.0
        
        val totalPoints = results.sumOf { result ->
            val gradeValue = scale[result.grade] ?: 0.0
            gradeValue * result.gpaContribution
        }
        val totalCredits = results.sumOf { it.gpaContribution }
        
        return if (totalCredits == 0.0) 0.0 else totalPoints / totalCredits
    }
}

class DeadlineTracker {
    fun isOverdue(deadline: Long, now: Long): Boolean {
        return deadline < now
    }

    fun daysUntil(deadline: Long, now: Long): Long {
        return (deadline - now) / (24 * 60 * 60 * 1000)
    }
}
