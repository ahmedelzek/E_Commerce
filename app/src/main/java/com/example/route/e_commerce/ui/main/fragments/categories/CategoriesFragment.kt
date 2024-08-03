package com.example.route.e_commerce.ui.main.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.route.e_commerce.R
import com.example.route.e_commerce.base.BaseFragment
import com.example.route.e_commerce.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding,CategoriesFragmentViewModel>() {

    private val myViewModel: CategoriesContract.ViewModel by viewModels<CategoriesFragmentViewModel>()


    override fun initViewModel(): CategoriesFragmentViewModel {
        return myViewModel as CategoriesFragmentViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_categories
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doAction(CategoriesContract.Action.InitPage)
    }
}