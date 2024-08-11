package com.example.route.e_commerce.ui.main.fragments.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.route.domain.model.Category
import com.example.route.domain.model.Product
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.base.showDialog
import com.example.route.e_commerce.databinding.FragmentHomeBinding
import com.example.route.e_commerce.ui.main.fragments.home.adapters.CategoriesAdapter
import com.example.route.e_commerce.ui.main.fragments.home.adapters.MostSellingProductsAdapter
import com.example.route.e_commerce.ui.main.fragments.home.adapters.ProductsAdapter
import com.example.route.e_commerce.utils.UserDataFiled
import com.example.route.e_commerce.utils.UserDataUtils
import com.example.route.e_commerce.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {
    private val categoriesAdapter = CategoriesAdapter()
    private val mostSellingProductsAdapter by lazy { MostSellingProductsAdapter(requireContext()) }
    private val productsAdapter by lazy { ProductsAdapter(requireContext()) }
    private var token: String? = null

    private val myViewModel: HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()

    override fun initViewModel(): HomeFragmentViewModel {
        return myViewModel as HomeFragmentViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        token = UserDataUtils().getUserData(requireContext(), UserDataFiled.TOKEN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.doAction(HomeContract.Action.InitPage(token = token!!))
    }

    private fun observeViewModel() {
        viewModel.event.observe(viewLifecycleOwner) {
            handleEvent(it)
        }
        lifecycleScope.launch {
            viewModel.state.collect{
                renderViewState(it)
            }
        }
    }

    private fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.ShowMessage->{
                showDialog(event.viewMessage)
            }

            is HomeContract.Event.AddedSuccessfully ->{
                productsAdapter.setWishlistData(event.wishlistItemsId)
                mostSellingProductsAdapter.setWishlistData(event.wishlistItemsId)
                showSnackBar(event.message)
            }
            is HomeContract.Event.RemovedSuccessfully -> {
                productsAdapter.setWishlistData(event.wishlistItemsId)
                mostSellingProductsAdapter.setWishlistData(event.wishlistItemsId)
                showSnackBar(event.message)
            }
        }
    }

    private fun renderViewState(state: HomeContract.State){
        when(state){
            is HomeContract.State.Success ->{
                showCategories(state.categories)
                showMostSellingProducts(state.mostSellingProducts)
                showProducts(state.products)
            }
            is HomeContract.State.LoadingState ->{
                showLoadingState()
            }
        }
    }


    private fun showCategories(categories: List<Category>?) {
        binding.categoriesRv.adapter = categoriesAdapter
        categories?.let {
            categoriesAdapter.bindCategories(it)
            binding.categoryNameTv.text = it[1].name
            viewModel.doAction(HomeContract.Action.LoadProducts(it[1].id!!))
            binding.categoriesShimmerViewContainer.visibility = View.INVISIBLE
        }
    }
    private fun showMostSellingProducts(products: List<Product>?) {
        binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        products?.let {
            mostSellingProductsAdapter.bindProducts(it)
            binding.mostSellingProductsShimmerViewContainer.visibility = View.INVISIBLE
        }
        mostSellingProductsAdapter.addProductToWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.AddProductToWishlist(it, product.id!!))
            }
        }
        mostSellingProductsAdapter.removeProductFromWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.RemoveProductFromWishlist(it, product.id!!))
            }
        }
    }
    private fun showProducts(products: List<Product>?) {
        binding.categoryProductsRv.adapter = productsAdapter
        products?.let {
            productsAdapter.bindProducts(it)
            binding.categoryProductsShimmerViewContainer.visibility = View.INVISIBLE
        }
        categoriesAdapter.categoryClicked = { _, category ->
            viewModel.doAction(HomeContract.Action.LoadProducts(category.id!!))
            binding.categoryNameTv.text = category.name
        }
        productsAdapter.addProductToWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.AddProductToWishlist(it, product.id!!))
            }
        }
        productsAdapter.removeProductFromWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.RemoveProductFromWishlist(it, product.id!!))
            }
        }
    }
    private fun showLoadingState(){
        binding.mostSellingProductsShimmerViewContainer.isVisible = true
        binding.categoriesShimmerViewContainer.isVisible = true
        binding.categoryProductsShimmerViewContainer.isVisible = true
    }

}
