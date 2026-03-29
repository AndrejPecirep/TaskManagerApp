package com.example.taskmanagerapp.data.mapper

import com.example.taskmanagerapp.data.local.entity.TaskEntity
import com.example.taskmanagerapp.domain.model.Task

fun TaskEntity.toDomain(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        isDone = isDone
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        isDone = isDone
    )
}