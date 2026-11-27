# Student OS — Engineering Implementation Plan

## Phase 1: Repository Audit & Project Setup
The current repository (`Daily-note`) is an empty shell (only README.md exists). We will initialize the **Student OS** Android application structure using the requested technology stack.

### Technology Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Repository Pattern
- **Database**: Room (Entities: StudentProfile, Semester, Subject, AttendanceRecord, TimetableEntry, AcademicNote, Assignment, Examination, ExaminationResult, StudySession, Reminder, AcademicGoal, AppSettings)
- **Navigation**: Navigation Compose
- **State Management**: StateFlow / Flow
- **Dependency Injection**: Hilt
- **Persistence**: DataStore (Preferences)
- **Background Tasks**: WorkManager
- **Testing**: JUnit 4/5, Espresso, Room Test Utils

## Phase 2: Design System Extraction (from image-1.png)
Based on the provided reference image (Apple/Notion/Linear inspired):
- **Color Tokens**: Primary (Deep Indigo/Blue), Surface (White/Light Gray), Accent (Green/Amber for attendance/status), Background (Neutral #F5F5F7).
- **Typography**: Inter/Roboto (Sans-serif), Hierarchy (H1: 28sp, H2: 20sp, Body: 16sp, Caption: 12sp).
- **Spacing**: 8dp base unit (8, 16, 24, 32, 48).
- **Components**: Rounded cards (16dp radius), subtle shadows (elevation 1-3dp), Bottom Navigation (Home, Attendance, Subjects, Planner, Profile).
- **Data Viz**: Minimalist bar/line charts for attendance/performance.

## Phase 3: Database Schema (Room Entities)
1. **StudentProfile**: id, name, university, avatar_path, settings_id
2. **Semester**: id, name, start_date, end_date, profile_id
3. **Subject**: id, name, code, type (Theory/Practical/Lab/Elective/Other), faculty, credits, attendance_req, color_hex, desc, semester_id
4. **AttendanceRecord**: id, subject_id, date, state (Present/Absent/Cancelled), lecture_number
5. **TimetableEntry**: id, subject_id, weekday (1-7), start_time, end_time, room, semester_id
6. **AcademicNote**: id, subject_id, title, content, type (Journal/Lecture/Concept/Todo), timestamp, pinned, favorite, tags
7. **Assignment**: id, subject_id, title, description, deadline, priority (Low/Medium/High/Urgent), status (Pending/Completed), reminder_time
8. **Examination**: id, subject_id, title, type (Internal/Semester/Practical/Viva), date_time, syllabus, prep_progress, marks_obtained, total_marks
9. **ExaminationResult**: id, exam_id, subject_id, grade, percentage, gpa_contribution
10. **StudySession**: id, subject_id, start_time, end_time, duration_mins, notes
11. **Reminder**: id, title, time, type (Class/Assignment/Exam/Goal), active
12. **AcademicGoal**: id, title, target_value, current_value, deadline, type (Attendance/GPA/StudyHours)
13. **AppSettings**: id, theme (Light/Dark), attendance_threshold, grading_scale_json, notification_enabled

## Phase 4: Core Engineering Implementation
1. **Project Initialization**: Gradle setup, Hilt modules, Room DB, Navigation graph.
2. **Domain Layer**: Attendance calculations, GPA logic, Deadline tracking, Study duration aggregation.
3. **Data Layer**: DAOs, Repositories, Migrations.
4. **Presentation Layer**:
   - **Navigation**: BottomNav (Home, Attendance, Subjects, Planner, Profile).
   - **Screens**:
     - Dashboard (Widgets: Schedule, Attendance %, Assignments, Exams, Notes, Study Progress, Reminders)
     - Attendance (Subject list, Date picker, Mark Present/Absent/Cancelled, Trends)
     - Subjects (CRUD, Details, Color selection)
     - Planner (Assignments, Exams, Study Sessions, Timetable)
     - Profile (Settings, Semester config, Goals, Profile info)
     - Notes (Editor, Search, Tags)
     - Timetable (Weekly view, Edit entries)
     - Performance (GPA calculator, Trends)
     - Calendar (Unified view)
5. **Offline-First**: Local DB as source of truth, WorkManager for local notifications.

## Phase 5: Implementation Execution
We will now begin creating the project structure and core files.

### Step 1: Create Project Structure
- `app/src/main/java/com/studentos/...`
  - `data/` (Entities, DAOs, Repositories, RoomDB)
  - `domain/` (UseCases, Models, Calculations)
  - `ui/` (Screens, Components, Theme, Navigation)
  - `di/` (Hilt Modules)
  - `utils/` (Constants, Extensions)
- `app/src/androidTest/` (UI Tests)
- `app/src/test/` (Unit Tests)

## Phase 6: Quality Assurance & Release
- Unit tests for attendance/GPA/deadline logic.
- UI tests for navigation and CRUD.
- Build release APK (signing config placeholder).
- Final Engineering Report.

---
**NEXT ACTION**: Initialize the Android project structure and build configuration.
