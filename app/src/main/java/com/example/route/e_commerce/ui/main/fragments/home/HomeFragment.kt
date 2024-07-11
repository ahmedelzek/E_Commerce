package com.example.route.e_commerce.ui.main.fragments.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.route.domain.model.Category
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.databinding.FragmentHomeBinding
import com.example.route.e_commerce.ui.main.fragments.home.adapters.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>() {
    private val categoriesAdapter = CategoriesAdapter()

    private val myViewModel: HomeFragmentViewModel by viewModels()

    override fun initViewModel(): HomeFragmentViewModel {
        return myViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }
    //private val mostSellingProductsAdapter = ProductsAdapter()
   // private val categoryProductsAdapter = ProductsAdapter()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
        viewModel.getCategories()
    }

    private fun observeViewModel() {
        viewModel.categories.observe(viewLifecycleOwner) {
            it?.let {
                categoriesAdapter.bindCategories(it)
            }
        }
    }

    private fun initViews() {
        categoriesAdapter.categoryClicked = { position, category ->
//            navigateToCategoriesFragment(category)
        }

        binding.categoriesRv.adapter = categoriesAdapter
       // binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
       // binding.categoryProductsRv.adapter = categoryProductsAdapter
        binding.categoryNameTv.text = getString(R.string.electronics)
//        categoryProductsAdapter.bindProducts()
//        mostSellingProductsAdapter.bindProducts()
//        categoriesAdapter.bindCategories()

    }


  /*  private fun navigateToProductDetailsFragment(product: Product) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(Product.PRODUCT, product)
        startActivity(intent)
    }*/



    private fun navigateToCategoriesFragment(category: Category) {
//        val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(category)
//        findNavController().navigate(action)
    }



    override fun onResume() {
        super.onResume()
//        binding.categoriesShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
//        binding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()

    }


}
