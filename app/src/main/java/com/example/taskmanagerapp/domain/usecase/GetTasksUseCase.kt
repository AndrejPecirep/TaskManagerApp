package com.example.taskmanagerapp.domain.usecase

import com.example.taskmanagerapp.domain.model.Task
import com.example.taskmanagerapp.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repository.getTasks()
}