package com.studentos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.studentos.ui.components.AcademicCard
import com.studentos.ui.components.BottomNavigationBar
import com.studentos.ui.components.StatusIndicator
import com.studentos.ui.theme.StudentOSTheme

@Composable
fun DashboardScreen(
    onNavigateTo: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header: Date & Welcome
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Thursday, Nov 27, 2026",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "Welcome back, Student",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Dashboard Widgets
        LazyColumn(modifier = Modifier.weight(1f)) {
            item {
                AcademicCard(
                    title = "Today's Schedule",
                    content = {
                        Text("No classes scheduled for today.")
                        // Schedule list implementation
                    }
                )
            }
            item {
                AcademicCard(
                    title = "Attendance Overview",
                    content = {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                Text("Overall", style = MaterialTheme.typography.bodyLarge)
                                Text("88%", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.primary)
                                StatusIndicator(com.studentos.domain.model.AttendanceStatus.SAFE)
                            }
                            // Subject-wise breakdown
                            Text("View Subjects", color = MaterialTheme.colorScheme.primary)
                        }
                    }
                )
            }
            item {
                AcademicCard(
                    title = "Upcoming Assignments",
                    content = {
                        Text("No pending assignments.")
                    }
                )
            }
            item {
                AcademicCard(
                    title = "Upcoming Examinations",
                    content = {
                        Text("Next: Calculus Midterm - Dec 5")
                    }
                )
            }
            item {
                AcademicCard(
                    title = "Recently Added Notes",
                    content = {
                        Text("Linear Algebra - Lecture 4")
                    }
                )
            }
            item {
                AcademicCard(
                    title = "Today's Study Progress",
                    content = {
                        Text("1h 20m completed")
                        LinearProgressIndicator(progress = 0.6f)
                    }
                )
            }
        }

        // Bottom Navigation
        BottomNavigationBar(selectedIndex = 0) { index ->
            onNavigateTo(when (index) {
                0 -> "Home"
                1 -> "Attendance"
                2 -> "Subjects"
                3 -> "Planner"
                else -> "Profile"
            })
        }
    }
}

@Composable
fun MainNavigationHost() {
    var currentScreen by remember { mutableStateOf("Home") }
    
    StudentOSTheme {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when (currentScreen) {
                    "Home" -> DashboardScreen { currentScreen = it }
                    "Attendance" -> Text("Attendance Management Screen")
                    "Subjects" -> Text("Subject Management Screen")
                    "Planner" -> Text("Planner & Tasks Screen")
                    "Profile" -> Text("Student Profile Screen")
                    else -> DashboardScreen { currentScreen = it }
                }
            }
        }
    }
}
