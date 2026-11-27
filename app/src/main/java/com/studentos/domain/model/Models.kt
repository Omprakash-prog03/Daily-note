package com.studentos.domain.model

import com.studentos.data.entity.*

data class AttendanceStats(
    val subject: Subject,
    val presentCount: Int,
    val conductedCount: Int,
    val percentage: Double,
    val status: AttendanceStatus
)

enum class AttendanceStatus { SAFE, WARNING, CRITICAL }

data class DashboardData(
    val todaySchedule: List<TimetableEntry>,
    val overallAttendance: Double,
    val subjectOverviews: List<AttendanceStats>,
    val upcomingAssignments: List<Assignment>,
    val upcomingExams: List<Examination>,
    val pendingTasks: List<AcademicNote>, // Todo type notes
    val recentNotes: List<AcademicNote>,
    val todayStudyProgress: Int, // Mins
    val importantReminders: List<Reminder>
)

data class CalculationResult(
    val value: Double,
    val message: String
)
