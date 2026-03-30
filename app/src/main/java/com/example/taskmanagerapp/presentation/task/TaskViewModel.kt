package com.example.taskmanagerapp.presentation.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagerapp.domain.model.Task
import com.example.taskmanagerapp.domain.usecase.AddTaskUseCase
import com.example.taskmanagerapp.domain.usecase.DeleteTaskUseCase
import com.example.taskmanagerapp.domain.usecase.GetTasksUseCase
import com.example.taskmanagerapp.domain.usecase.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TaskViewModel constructor(
    getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val selectedFilter = MutableStateFlow(TaskFilter.All)

    val uiState: StateFlow<TaskUiState> = combine(
        getTasksUseCase(),
        selectedFilter
    ) { tasks, filter ->
        TaskUiState(tasks = tasks, selectedFilter = filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), TaskUiState())

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            addTaskUseCase(
                Task(
                    title = title.trim(),
                    description = description.trim()
                )
            )
        }
    }

    fun toggleTask(task: Task) {
        viewModelScope.launch {
            updateTaskUseCase(task.copy(isDone = !task.isDone))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            deleteTaskUseCase(task)
        }
    }

    fun setFilter(filter: TaskFilter) {
        selectedFilter.update { filter }
    }
}
