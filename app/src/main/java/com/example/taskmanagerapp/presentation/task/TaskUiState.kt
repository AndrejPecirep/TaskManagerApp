package com.example.taskmanagerapp.presentation.task

import com.example.taskmanagerapp.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val selectedFilter: TaskFilter = TaskFilter.All
) {
    val visibleTasks: List<Task>
        get() = when (selectedFilter) {
            TaskFilter.All -> tasks
            TaskFilter.Open -> tasks.filterNot { it.isDone }
            TaskFilter.Done -> tasks.filter { it.isDone }
        }

    val completedCount: Int = tasks.count { it.isDone }
    val openCount: Int = tasks.count { !it.isDone }
}
