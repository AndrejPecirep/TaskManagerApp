package com.example.taskmanagerapp.domain.usecase

import com.example.taskmanagerapp.domain.model.Task
import com.example.taskmanagerapp.domain.repository.TaskRepository

class AddTaskUseCase constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.addTask(task)
}