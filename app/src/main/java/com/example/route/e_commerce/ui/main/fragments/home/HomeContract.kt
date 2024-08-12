package com.example.route.e_commerce.ui.main.fragments.home

import androidx.lifecycle.LiveData
import com.example.route.domain.model.Category
import com.example.route.domain.model.Product
import com.example.route.domain.model.WishlistItem
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class HomeContract{

    interface ViewModel{

        val state : StateFlow<State>
        val event : LiveData<Event>
        fun doAction(action: Action)
    }

    sealed class Action{
        data class InitPage(val token: String) : Action()

        data class LoadProducts(val categoryId: String) : Action()

        data class AddProductToWishlist(val token: String, val productId: String) : Action()

        data class RemoveProductFromWishlist(val token: String, val productId: String) : Action()
    }

    sealed class Event{
        data class ShowMessage(val viewMessage: ViewMessage): Event()

        data class AddedSuccessfully(
            val message: String,
            val wishlistItemsId: List<String>,
        ) : Event()

        data class RemovedSuccessfully(
            val message: String,
            val wishlistItemsId: List<String>,
        ) : Event()
    }
    sealed class State{
        data object LoadingState: State()
        data class Success(
            val categories: List<Category>? = null,
            val mostSellingProducts: List<Product>? = null,
            val products: List<Product>? = null,
            val wishlist: List<WishlistItem>? = null
        ): State()
    }

}
