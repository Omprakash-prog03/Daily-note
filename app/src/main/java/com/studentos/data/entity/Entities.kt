package com.studentos.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_profile")
data class StudentProfile(
    @PrimaryKey val id: Long = 0,
    val name: String,
    val university: String,
    val avatarPath: String?,
    val settingsId: Long
)

@Entity(tableName = "semester")
data class Semester(
    @PrimaryKey val id: Long = 0,
    val name: String,
    val startDate: Long, // Epoch milliseconds
    val endDate: Long,
    val profileId: Long
)

@Entity(tableName = "subject")
data class Subject(
    @PrimaryKey val id: Long = 0,
    val name: String,
    val code: String,
    val type: String, // Theory, Practical, Lab, Elective, Other
    val faculty: String?,
    val credits: Int,
    val attendanceReq: Double, // e.g., 75.0
    val colorHex: String,
    val description: String?,
    val semesterId: Long
)

@Entity(tableName = "attendance_record")
data class AttendanceRecord(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long,
    val date: Long, // Epoch (start of day)
    val state: String, // Present, Absent, Cancelled
    val lectureNumber: Int // Multiple lectures per day
)

@Entity(tableName = "timetable_entry")
data class TimetableEntry(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long,
    val weekday: Int, // 1-7 (Mon-Sun)
    val startTime: Long, // Minutes from midnight
    val endTime: Long,
    val room: String?,
    val semesterId: Long
)

@Entity(tableName = "academic_note")
data class AcademicNote(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long?, // Null for general notes
    val title: String,
    val content: String,
    val type: String, // Journal, Lecture, Concept, Todo
    val timestamp: Long,
    val pinned: Boolean = false,
    val favorite: Boolean = false,
    val tags: String? // Comma separated
)

@Entity(tableName = "assignment")
data class Assignment(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long,
    val title: String,
    val description: String?,
    val deadline: Long,
    val priority: String, // Low, Medium, High, Urgent
    val status: String, // Pending, Completed
    val reminderTime: Long?
)

@Entity(tableName = "examination")
data class Examination(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long,
    val title: String,
    val type: String, // Internal, Semester, Practical, Viva
    val dateTime: Long,
    val syllabus: String?,
    val prepProgress: Int, // 0-100
    val marksObtained: Double?,
    val totalMarks: Double?
)

@Entity(tableName = "examination_result")
data class ExaminationResult(
    @PrimaryKey val id: Long = 0,
    val examId: Long,
    val subjectId: Long,
    val grade: String,
    val percentage: Double,
    val gpaContribution: Double
)

@Entity(tableName = "study_session")
data class StudySession(
    @PrimaryKey val id: Long = 0,
    val subjectId: Long?,
    val startTime: Long,
    val endTime: Long,
    val durationMins: Int,
    val notes: String?
)

@Entity(tableName = "reminder")
data class Reminder(
    @PrimaryKey val id: Long = 0,
    val title: String,
    val time: Long,
    val type: String, // Class, Assignment, Exam, Goal
    val active: Boolean = true
)

@Entity(tableName = "academic_goal")
data class AcademicGoal(
    @PrimaryKey val id: Long = 0,
    val title: String,
    val targetValue: Double,
    val currentValue: Double,
    val deadline: Long,
    val type: String // Attendance, GPA, StudyHours
)

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey val id: Long = 0,
    val theme: String, // Light, Dark, System
    val attendanceThreshold: Double = 75.0,
    val gradingScaleJson: String?,
    val notificationEnabled: Boolean = true
)
