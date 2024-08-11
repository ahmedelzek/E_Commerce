package com.example.route.e_commerce.ui.main.fragments.wish_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.wish_list.DeleteProductFromWishlistUseCase
import com.example.route.domain.usecase.wish_list.GetLoggedUserWishlistUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel
@Inject
constructor(
    private val getLoggedUserWishlistUseCase: GetLoggedUserWishlistUseCase,
    private val deleteProductFromWishlist: DeleteProductFromWishlistUseCase,
) : BaseViewModel(), WishListContract.WishlistViewModel {
    private val _event = SingleLiveEvent<WishListContract.Event>()
    override val event: LiveData<WishListContract.Event>
        get() = _event

    private val _state = MutableStateFlow<WishListContract.State>(WishListContract.State.Loading)
    override val state: StateFlow<WishListContract.State>
        get() = _state

    override fun doAction(action: WishListContract.Action) {
        when (action) {
            is WishListContract.Action.InitWishlist -> loadWishlist(action.token)
            is WishListContract.Action.RemoveProduct ->
                removeProductFromWishlist(
                    action.token,
                    action.productId,
                )
        }
    }

    private fun loadWishlist(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getLoggedUserWishlistUseCase.invoke(token).collect { resourse ->
                when (resourse) {
                    is Resource.Success -> {
                        _state.emit(
                            WishListContract.State.Success(
                                wishlist = resourse.data
                            )
                        )
                    }

                    else -> {
                        extractViewMessage(resourse)?.let {
                            _event.postValue(WishListContract.Event.ErrorMessage(it))
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
            deleteProductFromWishlist.invoke(token, productId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _event.postValue(
                            WishListContract.Event.RemovedSuccessfully(
                                resource.data.message ?: "success",
                            ),
                        )
                    }

                    else -> {
                        extractViewMessage(resource)?.let {
                            _event.postValue(WishListContract.Event.ErrorMessage(it))
                        }
                    }
                }
            }
        }
    }
}
