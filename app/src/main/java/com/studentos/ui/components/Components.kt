package com.studentos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.studentos.ui.theme.*

@Composable
fun AcademicCard(
    title: String,
    subtitle: String? = null,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SurfaceColor,
    cornerShape: RoundedCornerShape = RoundedCornerShape(16.dp)
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = cornerShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor
            )
            subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
fun StatusIndicator(status: com.studentos.domain.model.AttendanceStatus) {
    val color = when (status) {
        com.studentos.domain.model.AttendanceStatus.SAFE -> AccentSuccess
        com.studentos.domain.model.AttendanceStatus.WARNING -> AccentWarning
        com.studentos.domain.model.AttendanceStatus.CRITICAL -> AccentCritical
    }
    
    Box(
        modifier = Modifier
            .size(12.dp)
            .background(color, shape = CircleShape),
        contentAlignment = Alignment.Center
    )
}

@Composable
fun BottomNavigationBar(
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar {
        val items = listOf(
            "Home", "Attendance", "Subjects", "Planner", "Profile"
        )
        items.forEachIndexed { index, label ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemClick(index) },
                label = { Text(label) },
                icon = { /* Icon logic here */ }
            )
        }
    }
}
