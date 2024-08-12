package com.example.route.e_commerce.ui.main.fragments.wish_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.route.domain.model.WishlistItem
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.base.showDialog
import com.example.route.e_commerce.databinding.FragmentWishListBinding
import com.example.route.e_commerce.ui.auth.AuthActivity
import com.example.route.e_commerce.ui.main.fragments.wish_list.adapter.WishListAdapter
import com.example.route.e_commerce.utils.UserDataFiled
import com.example.route.e_commerce.utils.UserDataUtils
import com.example.route.e_commerce.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WishListFragment : BaseFragment<FragmentWishListBinding, WishListViewModel>() {
    private val mViewModel: WishListContract.WishlistViewModel by viewModels<WishListViewModel>()
    private val adapter by lazy { WishListAdapter(requireContext()) }

    override fun initViewModel(): WishListViewModel {
        return mViewModel as WishListViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_wish_list
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        loadWishlist()
    }

    private fun loadWishlist() {
        val token = UserDataUtils().getUserData(requireContext(), UserDataFiled.TOKEN)
        if (token != null) {
            viewModel.doAction(WishListContract.Action.InitWishlist(token))
        } else {
            showDialog(
                message = "Login Again",
                posActionName = "Go login",
                posActionCallBack = {
                    startActivity(Intent(requireActivity(), AuthActivity::class.java))
                    requireActivity().finish()
                },
                isCancelable = false,
            )
        }
    }

    private fun observeData() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is WishListContract.Event.ErrorMessage -> showError(event.errorMessage.message)

                is WishListContract.Event.RemovedSuccessfully -> {
                    loadWishlist()
                    showSnackBar(event.message)
                }
            }
        }
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    WishListContract.State.Loading -> showLoading()
                    is WishListContract.State.Success -> initView(state.wishlist)
                }
            }
        }
    }

    private fun initView(
        wishlist: List<WishlistItem>?,
    ) {
        val token = UserDataUtils().getUserData(requireContext(), UserDataFiled.TOKEN)
        binding.errorView.isVisible = false
        binding.successView.isVisible = true
        binding.loadingView.isVisible = false
        if (wishlist != null) {
            if (wishlist.isEmpty()) {
                binding.emptyWishlist.isVisible = true
                binding.recyclerView.isVisible = false
            } else {
                binding.emptyWishlist.isGone = true
                binding.recyclerView.isVisible = true
                binding.recyclerView.adapter = adapter
                adapter.bindItems(wishlist)

                adapter.removeProductFromWishlist = { productId ->
                    token?.let {
                        viewModel.doAction(WishListContract.Action.RemoveProduct(it, productId))
                    }
                }
            }
        }
    }

    private fun showError(message: String) {
        binding.errorView.isVisible = true
        binding.successView.isVisible = false
        binding.loadingView.isVisible = false
        binding.errorText.text = message
        binding.tryAgainButton.setOnClickListener {
//            LoadWishList
            loadWishlist()
        }
    }

    private fun showLoading() {
        binding.errorView.isVisible = false
        binding.successView.isVisible = false
        binding.loadingView.isVisible = true
    }
}
