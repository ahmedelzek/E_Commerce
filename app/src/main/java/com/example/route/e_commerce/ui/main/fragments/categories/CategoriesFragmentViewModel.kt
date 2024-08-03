package com.example.route.e_commerce.ui.main.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.GetCategoriesUseCase
import com.example.route.domain.usecase.GetProductsUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel(), CategoriesContract.ViewModel {


    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke()
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            _state.emit(
                                CategoriesContract.State.Success(
                                    categories = resourse.data
                                )
                            )
                        }
                        else -> {
                            extractViewMessage(resourse)?.let {
                                _event.postValue(CategoriesContract.Event.ShowMessage(it))
                            }
                        }
                    }
                }
        }

    }

    private fun getProducts(categoryId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke(categoryId)
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            _state.emit(
                                CategoriesContract.State.Success(
                                    products = resourse.data
                                )
                            )
                        }

                        else -> {
                            extractViewMessage(resourse)?.let {
                                _event.postValue(CategoriesContract.Event.ShowMessage(it))
                            }
                        }
                    }
                }
        }
    }


    private val _state =
        MutableStateFlow<CategoriesContract.State>(CategoriesContract.State.LoadingState)
    override val state: StateFlow<CategoriesContract.State>
        get() = _state

    private val _event = SingleLiveEvent<CategoriesContract.Event>()
    override val event: LiveData<CategoriesContract.Event>
        get() = _event

    override fun doAction(action: CategoriesContract.Action) {
        when (action) {
            is CategoriesContract.Action.InitPage -> {
                initPage()
            }

            is CategoriesContract.Action.LoadProducts -> {
            }
        }
    }

    private fun initPage() {
    }
}