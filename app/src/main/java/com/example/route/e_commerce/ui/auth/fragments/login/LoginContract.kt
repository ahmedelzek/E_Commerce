package com.example.route.e_commerce.ui.auth.fragments.login

import androidx.lifecycle.LiveData
import com.example.route.domain.model.AuthResponse
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class LoginContract {
    interface LoginViewModel {
        val event: LiveData<Event>
        val state: StateFlow<State>

        fun doAction(action: Action)
    }

    sealed class Action {
        data object Login : Action()
    }

    sealed class Event {
        data class ErrorMessage(val message: ViewMessage) : Event()
    }

    sealed class State {
        data object Pending : State()

        data object Logging : State()

        data class Logged(val response: AuthResponse) : State()
    }
}