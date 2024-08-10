package com.example.route.e_commerce.ui.main.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.route.domain.model.Category
import com.example.route.domain.model.Subcategory
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.base.showDialog
import com.example.route.e_commerce.databinding.FragmentCategoriesBinding
import com.example.route.e_commerce.ui.main.fragments.categories.adapters.SubcategoriesAdapter
import com.example.route.e_commerce.ui.main.fragments.categories.adapters.CategoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding,CategoriesFragmentViewModel>() {

    private val categoriesAdapter = CategoriesAdapter()
    private val subcategoriesAdapter = SubcategoriesAdapter()
    private val myViewModel: CategoriesContract.ViewModel by viewModels<CategoriesFragmentViewModel>()


    override fun initViewModel(): CategoriesFragmentViewModel {
        return myViewModel as CategoriesFragmentViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_categories
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.doAction(CategoriesContract.Action.InitPage)
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

    private fun handleEvent(event: CategoriesContract.Event) {
        when (event) {
            is CategoriesContract.Event.ShowMessage->{
                showDialog(event.viewMessage)
            }
        }
    }

    private fun renderViewState(state: CategoriesContract.State){
        when(state){
            is CategoriesContract.State.Success ->{
                showCategories(state.categories)
                showSubcategories(state.subcategoriesList)
            }
            is CategoriesContract.State.LoadingState ->{
                binding.categoriesShimmerViewContainer.isVisible = true
            }
        }
    }
    private fun showCategories(categories: List<Category>?) {
        binding.categoriesRv.adapter = categoriesAdapter
        categories?.let {
            categoriesAdapter.bindCategories(it)
            binding.selectedCategoryName.text = it[1].name
            viewModel.doAction(CategoriesContract.Action.LoadSubcategories(it[1].id!!))
            binding.categoriesShimmerViewContainer.visibility = View.INVISIBLE
        }
    }
    private fun showSubcategories(subcategories: List<Subcategory>?) {
        binding.subcategoryRv.adapter = subcategoriesAdapter
        subcategories?.let {
            subcategoriesAdapter.bindSubcategories(it)
        }
        categoriesAdapter.categoryClicked = { _, category ->
            viewModel.doAction(CategoriesContract.Action.LoadSubcategories(category.id!!))
            binding.selectedCategoryName.text = category.name
        }
    }
}