package com.example.route.e_commerce.ui.auth.fragments.forget_password

import androidx.lifecycle.LiveData
import com.example.route.e_commerce.base.ViewMessage
import kotlinx.coroutines.flow.StateFlow

class ForgetPasswordContract {
    interface ForgetPasswordViewModel {
        val event: LiveData<Event>
        val state: StateFlow<State>

        fun doAction(action: Action)
    }

    sealed class Action {
        data object SendResetCode : Action()
    }

    sealed class Event {
        data class ErrorMessage(val message: ViewMessage) : Event()
    }

    sealed class State {
        data object Idle : State()

        data object Logging : State()

        data object EmailSent : State()
    }
}
