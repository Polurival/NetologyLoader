package com.polurival.netologyloader.data.service

import com.polurival.netologyloader.data.model.TaskResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class NetologyService(retrofit: Retrofit) {

    private val taskService by lazy { retrofit.create(TaskService::class.java) }

    suspend fun getAllTasks(): TaskResponse = withContext(Dispatchers.Default) {
        taskService.getTasks()
    }
}