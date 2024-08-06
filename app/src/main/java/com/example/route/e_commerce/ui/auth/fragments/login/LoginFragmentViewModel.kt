package com.example.route.e_commerce.ui.auth.fragments.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.route.domain.common.Resource
import com.example.route.domain.usecase.auth.LoginUseCase
import com.example.route.e_commerce.base.BaseViewModel
import com.example.route.e_commerce.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase
) :
    BaseViewModel(), LoginContract.LoginViewModel {
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()

    private val _event = SingleLiveEvent<LoginContract.Event>()
    override val event: LiveData<LoginContract.Event>
        get() = _event

    private val _state = MutableStateFlow<LoginContract.State>(LoginContract.State.Pending)
    override val state: StateFlow<LoginContract.State>
        get() = _state

    override fun doAction(action: LoginContract.Action) {
        when (action) {
            LoginContract.Action.Login -> login()
        }
    }

    private fun login() {
        if (!validate()) return
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(LoginContract.State.Logging)
            Log.e("Loading->", "isLoading")
            delay(1000)
            loginUseCase.invoke(
                emailLiveData.value!!,
                passwordLiveData.value!!,
            ).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.emit(LoginContract.State.Logged(resource.data!!))
                    }

                    else -> {
                        _state.emit(LoginContract.State.Pending)
                        extractViewMessage(resource)?.let {
                            _event.postValue(LoginContract.Event.ErrorMessage(it))
                        }
                    }
                }
            }
        }
    }

    private fun validate(): Boolean {
        var isValid = true
        if (emailLiveData.value.isNullOrEmpty()) {
            emailError.value = "Please Enter Valid Email..!"
            isValid = false
        } else {
            emailError.value = null
        }
        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordError.value = "Please Enter Valid Password..!"
            isValid = false
        } else {
            passwordError.value = null
        }
        return isValid
    }
}
