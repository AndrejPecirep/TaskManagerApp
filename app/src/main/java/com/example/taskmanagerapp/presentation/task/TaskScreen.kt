package com.example.taskmanagerapp.presentation.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.taskmanagerapp.presentation.theme.spacing
import com.example.taskmanagerapp.presentation.task.components.AddTaskDialog
import com.example.taskmanagerapp.presentation.task.components.EmptyTasksState
import com.example.taskmanagerapp.presentation.task.components.TaskFilterChips
import com.example.taskmanagerapp.presentation.task.components.TaskItem
import com.example.taskmanagerapp.presentation.task.components.TaskOverviewCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("TaskMaster")
                        Text(
                            text = "A clean place for your daily priorities",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add task")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                item { TaskOverviewCard(uiState) }
                item {
                    TaskFilterChips(
                        selectedFilter = uiState.selectedFilter,
                        onSelected = viewModel::setFilter
                    )
                }

                if (uiState.visibleTasks.isEmpty()) {
                    item {
                        EmptyTasksState(
                            message = if (uiState.tasks.isEmpty()) {
                                "Tap the add button to create your first task."
                            } else {
                                "Try switching the filter to see the rest of your tasks."
                            }
                        )
                    }
                } else {
                    items(
                        items = uiState.visibleTasks,
                        key = { it.id }
                    ) { task ->
                        TaskItem(
                            task = task,
                            onToggleDone = viewModel::toggleTask,
                            onDelete = viewModel::deleteTask
                        )
                    }
                }
            }

            if (showDialog) {
                AddTaskDialog(
                    onAdd = { title, description -> viewModel.addTask(title, description) },
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}
