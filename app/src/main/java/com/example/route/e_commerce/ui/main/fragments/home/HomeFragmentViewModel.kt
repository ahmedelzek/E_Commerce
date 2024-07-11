package com.example.route.e_commerce.ui.main.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.model.Category
import com.example.route.domain.usecase.GetCategoriesUseCase
import com.example.route.e_commerce.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : BaseViewModel() {


    val categories = MutableLiveData<List<Category>?>()

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.invoke()
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            categories.postValue(resourse.data)
                        }
                        else ->{
                            extractViewMessage(resourse)
                        }
                    }
                }
        }

    }
}