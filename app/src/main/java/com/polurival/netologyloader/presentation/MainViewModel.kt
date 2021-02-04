package com.polurival.netologyloader.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polurival.netologyloader.data.service.NetologyRepository
import com.polurival.netologyloader.presentation.model.SubjectItem
import com.polurival.netologyloader.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author Польщиков Юрий on 04/02/2021
 */
class MainViewModel(private val netologyRepository: NetologyRepository) : ViewModel() {

    private val uiState = MutableLiveData<Resource<List<SubjectItem>>>()

    fun getUsers(): LiveData<Resource<List<SubjectItem>>> {
        return uiState
    }

    fun requestSubjects() {
        viewModelScope.launch {
            uiState.postValue(Resource.loading(null))
            netologyRepository.tasksFlow
                .catch { uiState.value = Resource.error(it.message.orEmpty(), null) }
                .collect { uiState.value = Resource.success(it) }
        }
    }
}