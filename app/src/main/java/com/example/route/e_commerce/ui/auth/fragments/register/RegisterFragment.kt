package com.example.route.e_commerce.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.route.domain.model.AuthResponse
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.databinding.FragmentRegisterBinding
import com.example.route.e_commerce.ui.main.MainActivity
import com.example.route.e_commerce.utils.UserDataFiled
import com.example.route.e_commerce.utils.UserDataUtils
import com.example.route.e_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterFragmentViewModel>() {
    private val mViewModel: RegisterContract.RegisterViewModel by viewModels<RegisterFragmentViewModel>()

    override fun initViewModel(): RegisterFragmentViewModel {
        return mViewModel as RegisterFragmentViewModel
    }

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initViews()
        observeData()
    }

    private fun observeData() {
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                is RegisterContract.Event.ErrorMessage -> {
                    showErrorView(it.message.message)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    RegisterContract.State.Registering -> showLoadingView()

                    is RegisterContract.State.Registered -> navigateToHome(state.user)

                    RegisterContract.State.Pending -> showSuccessView()
                }
            }
        }
    }

    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
        binding.registerBtn.setOnClickListener {
            // register
            viewModel.doAction(RegisterContract.Action.Register)
        }
        binding.loginDoHaveAccountTv.setOnClickListener {
            // go to login
            navigateToLogin()
        }
    }

    private fun showSuccessView() {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
    }

    private fun showErrorView(message: String) {
        binding.icNext.isVisible = true
        binding.progressBar.isVisible = false
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(requireContext().getColor(R.color.white))
            .show()
    }

    private fun showLoadingView() {
        binding.icNext.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun navigateToHome(userData: AuthResponse) {
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.TOKEN, userData.token)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.ROLE, userData.user?.role)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.NAME, userData.user?.name)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.EMAIL, userData.user?.email)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.CART_ITEM_COUNT, null)
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun hideKeyboard() {
        view?.hideKeyboard(activity as AppCompatActivity?)
    }
}
