package com.polurival.netologyloader.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.polurival.netologyloader.data.service.NetologyRepository
import com.polurival.netologyloader.presentation.MainViewModel

/**
 * todo universal generic viewModel factory
 *
 * @author Польщиков Юрий on 04/02/2021
 */
class MainViewModelFactory(private val netologyRepository: NetologyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(netologyRepository) as T
}