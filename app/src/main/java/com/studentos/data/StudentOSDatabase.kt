package com.studentos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studentos.data.dao.*
import com.studentos.data.entity.*

@Database(
    entities = [
        StudentProfile::class, Semester::class, Subject::class, AttendanceRecord::class,
        TimetableEntry::class, AcademicNote::class, Assignment::class, Examination::class,
        ExaminationResult::class, StudySession::class, Reminder::class, AcademicGoal::class,
        AppSettings::class
    ],
    version = 1,
    exportSchema = false
)
abstract class StudentOSDatabase : RoomDatabase() {
    abstract fun studentProfileDao(): StudentProfileDao
    abstract fun semesterDao(): SemesterDao
    abstract fun subjectDao(): SubjectDao
    abstract fun attendanceDao(): AttendanceDao
    abstract fun timetableDao(): TimetableDao
    abstract fun academicNoteDao(): AcademicNoteDao
    abstract fun assignmentDao(): AssignmentDao
    abstract fun examinationDao(): ExaminationDao
    abstract fun examinationResultDao(): ExaminationResultDao
    abstract fun studySessionDao(): StudySessionDao
    abstract fun reminderDao(): ReminderDao
    abstract fun academicGoalDao(): AcademicGoalDao
    abstract fun appSettingsDao(): AppSettingsDao

    companion object {
        @Volatile
        private var INSTANCE: StudentOSDatabase? = null

        fun getDatabase(context: Context): StudentOSDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentOSDatabase::class.java,
                    "student_os_db"
                )
                    .fallbackToDestructiveMigration() // For development phase; replace with migrations later
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
