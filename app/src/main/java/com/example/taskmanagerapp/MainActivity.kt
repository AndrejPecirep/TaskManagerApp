package com.example.taskmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanagerapp.core.database.TaskDatabase
import com.example.taskmanagerapp.data.repository.TaskRepositoryImpl
import com.example.taskmanagerapp.domain.usecase.AddTaskUseCase
import com.example.taskmanagerapp.domain.usecase.DeleteTaskUseCase
import com.example.taskmanagerapp.domain.usecase.GetTasksUseCase
import com.example.taskmanagerapp.domain.usecase.UpdateTaskUseCase
import com.example.taskmanagerapp.presentation.task.TaskScreen
import com.example.taskmanagerapp.presentation.task.TaskViewModel
import com.example.taskmanagerapp.presentation.theme.TaskMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = TaskDatabase.getInstance(applicationContext).taskDao
        val repository = TaskRepositoryImpl(dao)
        val factory = TaskViewModelFactory(
            getTasksUseCase = GetTasksUseCase(repository),
            addTaskUseCase = AddTaskUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
            deleteTaskUseCase = DeleteTaskUseCase(repository)
        )
        val viewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]

        setContent {
            TaskMasterTheme {
                TaskScreen(viewModel = viewModel)
            }
        }
    }
}

private class TaskViewModelFactory(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(
                getTasksUseCase = getTasksUseCase,
                addTaskUseCase = addTaskUseCase,
                updateTaskUseCase = updateTaskUseCase,
                deleteTaskUseCase = deleteTaskUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
