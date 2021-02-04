package com.polurival.netologyloader.data.service

import com.polurival.netologyloader.data.model.TaskResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class NetologyService {

    //todo move to DI
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gitcdn.link/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val taskService = retrofit.create(TaskService::class.java)

    suspend fun getAllTasks(): TaskResponse = withContext(Dispatchers.Default) {
        taskService.getTasks()
    }
}