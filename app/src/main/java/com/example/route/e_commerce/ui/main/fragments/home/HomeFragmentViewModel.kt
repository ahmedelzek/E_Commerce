package com.example.route.e_commerce.ui.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.category.GetCategoriesUseCase
import com.example.route.domain.usecase.product.GetMostSellingProductsUseCase
import com.example.route.domain.usecase.product.GetProductsUseCase
import com.example.route.domain.usecase.wish_list.AddProductToWishlistUseCase
import com.example.route.domain.usecase.wish_list.DeleteProductFromWishlistUseCase
import com.example.route.domain.usecase.wish_list.GetLoggedUserWishlistUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.ui.main.fragments.wish_list.WishListContract
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
    private val getMostSellingProductsUseCase: GetMostSellingProductsUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToWishlistUseCase: AddProductToWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase,
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase
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

    private fun getProducts(categoryId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.invoke(categoryId)
                .collect { resourse ->
                    when (resourse) {
                        is Resource.Success -> {
                            _state.emit(
                                HomeContract.State.Success(
                                    products = resourse.data
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

    private fun loadWishlist(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getLoggedUserWishlistUseCase.invoke(token).collect { resourse ->
                when (resourse) {
                    is Resource.Success -> {
                        _state.emit(
                            HomeContract.State.Success(
                                wishlist = resourse.data
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

    private fun addProductToWishlist(
        token: String,
        productId: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addProductToWishlistUseCase.invoke(token, productId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _event.postValue(
                            HomeContract.Event.AddedSuccessfully(
                                resource.data.message!!,
                                resource.data.data!!,
                            ),
                        )
                    }

                    else -> {
                        extractViewMessage(resource)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }

    private fun removeProductFromWishlist(
        token: String,
        productId: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteProductFromWishlistUseCase.invoke(token, productId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _event.postValue(
                            HomeContract.Event.RemovedSuccessfully(
                                resource.data.message!!,
                                resource.data.data!!,
                            ),
                        )
                    }

                    else -> {
                        extractViewMessage(resource)?.let {
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
            is HomeContract.Action.InitPage -> {
                initPage(action.token)
            }
            is HomeContract.Action.LoadProducts -> {
                getProducts(action.categoryId)
            }

            is HomeContract.Action.AddProductToWishlist -> {
                addProductToWishlist(
                    action.token,
                    action.productId,
                )
            }
            is HomeContract.Action.RemoveProductFromWishlist -> {
                removeProductFromWishlist(
                    action.token,
                    action.productId,
                )
            }
        }
    }

    private fun initPage(token: String) {
        getCategories()
        getMostSellingProducts()
        loadWishlist(token)
    }
}
