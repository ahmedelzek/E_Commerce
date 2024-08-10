package com.example.route.e_commerce.ui.auth.fragments.register

import androidx.lifecycle.LiveData
import com.example.route.domain.model.AuthResponse
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class RegisterContract {
    interface RegisterViewModel {
        val event: LiveData<Event>
        val state: StateFlow<State>

        fun doAction(action: Action)
    }

    sealed class Action {
        data object Register : Action()
    }

    sealed class Event {
        class ErrorMessage(val message: ViewMessage) : Event()
    }

    sealed class State {
        data object Pending : State()

        data object Registering : State()

        data class Registered(val user: AuthResponse) : State()
    }
}
