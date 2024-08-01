package com.example.route.e_commerce.ui.main.fragments.home

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
import com.example.route.e_commerce.ui.main.fragments.home.adapters.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {
    private val categoriesAdapter = CategoriesAdapter()
    private val mostSellingProductsAdapter = ProductsAdapter()

    private val myViewModel: HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()

    override fun initViewModel(): HomeFragmentViewModel {
        return myViewModel as HomeFragmentViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        viewModel.doAction(HomeContract.Action.InitPage)
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
        }
    }

    private fun renderViewState(state: HomeContract.State){
        when(state){
            is HomeContract.State.Success ->{
                showCategories(state.categories)
                showMostSellingProducts(state.mostSellingProducts)
            }
            is HomeContract.State.LoadingState ->{
                showLoadingState()
            }
        }
    }
    private fun showCategories(categories: List<Category>?) {
        categories?.let {
            categoriesAdapter.bindCategories(it)
            binding.categoriesShimmerViewContainer.visibility = View.INVISIBLE
        }
    }
    private fun showMostSellingProducts(products: List<Product>?) {
        products?.let {
            mostSellingProductsAdapter.bindProducts(it)
            binding.mostSellingProductsShimmerViewContainer.visibility = View.INVISIBLE
        }
    }
    private fun showLoadingState(){
        binding.mostSellingProductsShimmerViewContainer.isVisible = true
        binding.categoriesShimmerViewContainer.isVisible = true
        binding.categoryProductsShimmerViewContainer.isVisible = true
    }


    private fun initViews() {
        categoriesAdapter.categoryClicked = { _, category ->
            binding.categoryNameTv.text = category.name
        }

        binding.categoriesRv.adapter = categoriesAdapter
       binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()

    }


}
