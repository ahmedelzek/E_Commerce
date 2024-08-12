package com.example.route.e_commerce.ui.main.fragments.wish_list

import androidx.lifecycle.LiveData
import com.example.route.domain.model.WishlistItem
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class WishListContract {
    interface WishlistViewModel {
        val event: LiveData<Event>
        val state: StateFlow<State>

        fun doAction(action: Action)
    }

    sealed class Action {
        data class InitWishlist(val token: String) : Action()

        data class RemoveProduct(val token: String, val productId: String) : Action()
    }

    sealed class Event {
        data class ErrorMessage(val errorMessage: ViewMessage) : Event()


        data class RemovedSuccessfully(val message: String) : Event()
    }

    sealed class State {
        data object Loading : State()

        data class Success(val wishlist: List<WishlistItem>?) : State()
    }
}
