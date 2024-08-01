package com.example.route.e_commerce.ui.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.GetCategoriesUseCase
import com.example.route.domain.usecase.GetMostSellingProductsUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getMostSellingProductsUseCase: GetMostSellingProductsUseCase
) : BaseViewModel(), HomeContract.ViewModel {

    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke()
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            _state.emit(
                                HomeContract.State.Success(
                                    categories = resourse.data
                                )
                            )
                        }

                        else -> {
                            extractViewMessage(resourse)?.let {
                                _event.postValue(HomeContract.Event.ShowMessage(it))
                            }
                        }
                    }
                }
        }

    }

    private fun getMostSellingProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getMostSellingProductsUseCase.invoke()
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            _state.emit(
                                HomeContract.State.Success(
                                    mostSellingProducts = resourse.data
                                )
                            )
                        }

                        else -> {
                            extractViewMessage(resourse)?.let {
                                _event.postValue(HomeContract.Event.ShowMessage(it))
                            }
                        }
                    }
                }
        }
    }

    private val _state = MutableStateFlow<HomeContract.State>(HomeContract.State.LoadingState)
    override val state: StateFlow<HomeContract.State>
        get() = _state

    private val _event = SingleLiveEvent<HomeContract.Event>()
    override val event: LiveData<HomeContract.Event>
        get() = _event

    override fun doAction(action: HomeContract.Action) {
        when (action) {
            HomeContract.Action.InitPage -> {
                initPage()
            }
        }
    }

    private fun initPage() {
        getCategories()
        getMostSellingProducts()
    }
}
