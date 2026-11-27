package com.studentos.data.dao

import androidx.room.*
import com.studentos.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: StudentProfile)

    @Query("SELECT * FROM student_profile WHERE id = :id")
    fun getProfile(id: Long): Flow<StudentProfile>

    @Update
    suspend fun updateProfile(profile: StudentProfile)
}

@Dao
interface SemesterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSemester(semester: Semester)

    @Query("SELECT * FROM semester WHERE profileId = :profileId")
    fun getSemestersByProfile(profileId: Long): Flow<List<Semester>>
}

@Dao
interface SubjectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Query("SELECT * FROM subject WHERE semesterId = :semesterId")
    fun getSubjectsBySemester(semesterId: Long): Flow<List<Subject>>

    @Query("DELETE FROM subject WHERE id = :id")
    suspend fun deleteSubject(id: Long)
}

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: AttendanceRecord)

    @Query("SELECT * FROM attendance_record WHERE subjectId = :subjectId ORDER BY date DESC")
    fun getRecordsBySubject(subjectId: Long): Flow<List<AttendanceRecord>>

    @Query("SELECT COUNT(*) FROM attendance_record WHERE subjectId = :subjectId AND state = 'Present'")
    fun getPresentCount(subjectId: Long): Flow<Int>

    @Query("SELECT COUNT(*) FROM attendance_record WHERE subjectId = :subjectId AND state != 'Cancelled'")
    fun getConductedCount(subjectId: Long): Flow<Int>
}

@Dao
interface TimetableDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: TimetableEntry)

    @Query("SELECT * FROM timetable_entry WHERE semesterId = :semesterId AND weekday = :weekday ORDER BY startTime")
    fun getEntriesByDay(semesterId: Long, weekday: Int): Flow<List<TimetableEntry>>
}

@Dao
interface AcademicNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: AcademicNote)

    @Query("SELECT * FROM academic_note WHERE subjectId = :subjectId ORDER BY timestamp DESC")
    fun getNotesBySubject(subjectId: Long?): Flow<List<AcademicNote>>

    @Query("SELECT * FROM academic_note WHERE pinned = 1 ORDER BY timestamp DESC")
    fun getPinnedNotes(): Flow<List<AcademicNote>>
}

@Dao
interface AssignmentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAssignment(assignment: Assignment)

    @Query("SELECT * FROM assignment WHERE subjectId = :subjectId AND status = 'Pending'")
    fun getPendingAssignments(subjectId: Long): Flow<List<Assignment>>

    @Query("SELECT * FROM assignment WHERE deadline < :now AND status = 'Pending'")
    fun getOverdueAssignments(now: Long): Flow<List<Assignment>>
}

@Dao
interface ExaminationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: Examination)

    @Query("SELECT * FROM examination WHERE subjectId = :subjectId ORDER BY dateTime ASC")
    fun getExamsBySubject(subjectId: Long): Flow<List<Examination>>

    @Query("SELECT * FROM examination WHERE dateTime > :now ORDER BY dateTime ASC LIMIT 1")
    fun getNextExam(now: Long): Flow<Examination?>
}

@Dao
interface StudySessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: StudySession)

    @Query("SELECT SUM(durationMins) FROM study_session WHERE subjectId = :subjectId AND startTime >= :start AND startTime <= :end")
    fun getTotalStudyTime(subjectId: Long, start: Long, end: Long): Flow<Int?>
}

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: Reminder)

    @Query("SELECT * FROM reminder WHERE active = 1 AND time > :now")
    fun getUpcomingReminders(now: Long): Flow<List<Reminder>>
}

@Dao
interface AcademicGoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: AcademicGoal)

    @Query("SELECT * FROM academic_goal")
    fun getAllGoals(): Flow<List<AcademicGoal>>
}

@Dao
interface AppSettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: AppSettings)

    @Query("SELECT * FROM app_settings WHERE id = 0")
    fun getSettings(): Flow<AppSettings?>
}
