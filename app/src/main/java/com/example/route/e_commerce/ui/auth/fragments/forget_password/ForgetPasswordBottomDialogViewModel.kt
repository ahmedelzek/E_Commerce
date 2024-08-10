package com.example.route.e_commerce.ui.auth.fragments.forget_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.auth.SendResetCodeUseCase
import com.example.route.domain.usecase.auth.ValidationUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordBottomDialogViewModel
    @Inject
    constructor(
        private val validationUseCase: ValidationUseCase,
        private val sentResetCodeUseCase: SendResetCodeUseCase,
    ) :
    BaseViewModel(),
    ForgetPasswordContract.ForgetPasswordViewModel {
        private val _event = SingleLiveEvent<ForgetPasswordContract.Event>()
        override val event: LiveData<ForgetPasswordContract.Event>
            get() = _event

        private val _state =
            MutableStateFlow<ForgetPasswordContract.State>(ForgetPasswordContract.State.Idle)
        override val state: StateFlow<ForgetPasswordContract.State>
            get() = _state

        val emailLiveData = MutableLiveData<String>()
        val emailError = MutableLiveData<String?>()

        override fun doAction(action: ForgetPasswordContract.Action) {
            when (action) {
                is ForgetPasswordContract.Action.SendResetCode -> sendResetCode()
            }
        }

        private fun sendResetCode() {
            if (!validEmail()) return
            viewModelScope.launch {
                _state.emit(ForgetPasswordContract.State.Logging)
                delay(1000)
                sentResetCodeUseCase(emailLiveData.value!!).collect { resource ->
                    when (resource) {
                        is Resource.Success -> _state.emit(ForgetPasswordContract.State.EmailSent)

                        else -> {
                            _state.emit(ForgetPasswordContract.State.Idle)
                            extractViewMessage(resource)?.let {
                                emailError.postValue(it.message)
                            }
                        }
                    }
                }
            }
        }

        private fun validEmail(): Boolean {
            var isValid = true
            if (validationUseCase.isValidEmail(emailLiveData.value)) {
                emailError.value = null
            } else {
                emailError.value = "Please enter valid email"
                isValid = false
            }
            return isValid
        }
    }
