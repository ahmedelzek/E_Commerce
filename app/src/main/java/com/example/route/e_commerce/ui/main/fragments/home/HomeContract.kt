package com.example.route.e_commerce.ui.main.fragments.home

import androidx.lifecycle.LiveData
import com.example.route.domain.model.Category
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class HomeContract{

    interface ViewModel{

        val state : StateFlow<State>
        val event : LiveData<Event>
        fun doAction(action: Action)
    }

    sealed class Action{
        data object InitPage : Action()
    }

    sealed class Event{
        data class ShowMessage(val viewMessage: ViewMessage): Event()
    }
    sealed class State{
        data object LoadingState: State()
        data class Success(
            val categories: List<Category>? = null
        ): State()
    }

}
