package com.polurival.netologyloader.data.service

import com.polurival.netologyloader.data.model.TaskResponse
import retrofit2.http.GET

/**
 * @author Польщиков Юрий on 04/02/2021
 */
interface TaskService {
    @GET("repo/netology-code/rn-task/master/netology.json")
    suspend fun getTasks(): TaskResponse
}
