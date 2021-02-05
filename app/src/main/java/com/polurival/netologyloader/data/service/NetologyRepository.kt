package com.polurival.netologyloader.data.service

import com.polurival.netologyloader.presentation.model.SubjectItem
import com.polurival.netologyloader.utils.ResponseConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class NetologyRepository(
    private val netologyService: NetologyService,
    private val defaultDispatcher: CoroutineDispatcher
) {
    private val responseConverter = ResponseConverter()

    // todo add caching in database and reading from it
    val tasksFlow: Flow<List<SubjectItem>>
        get() = flow { emit(responseConverter.convert(netologyService.getAllTasks())) }
            .flowOn(defaultDispatcher)
}