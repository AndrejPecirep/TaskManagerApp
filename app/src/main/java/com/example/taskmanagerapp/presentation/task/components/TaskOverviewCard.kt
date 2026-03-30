package com.example.taskmanagerapp.presentation.task.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.taskmanagerapp.presentation.theme.spacing
import com.example.taskmanagerapp.presentation.task.TaskUiState

@Composable
fun TaskOverviewCard(uiState: TaskUiState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Text(
                text = "Stay on top of your day",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "${uiState.openCount} open tasks · ${uiState.completedCount} completed",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = MaterialTheme.spacing.medium),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TaskMetric(label = "Total", value = uiState.tasks.size.toString())
                TaskMetric(label = "Open", value = uiState.openCount.toString())
                TaskMetric(label = "Done", value = uiState.completedCount.toString())
            }
        }
    }
}

@Composable
private fun TaskMetric(label: String, value: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
        )
    }
}
