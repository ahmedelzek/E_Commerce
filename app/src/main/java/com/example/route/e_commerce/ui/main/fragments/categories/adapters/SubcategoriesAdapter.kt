package com.example.route.e_commerce.ui.main.fragments.categories.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.route.domain.model.Subcategory
import com.example.route.e_commerce.databinding.ItemSubcategoryBinding

class SubcategoriesAdapter(private var subcategories: List<Subcategory>? = null) :
    RecyclerView.Adapter<SubcategoriesAdapter.ViewHolder>() {
    class ViewHolder(private val itemSubcategoryBinding: ItemSubcategoryBinding) :
        RecyclerView.ViewHolder(itemSubcategoryBinding.root) {
        fun bind(subcategory: Subcategory?) {
            itemSubcategoryBinding.subcategory = subcategory
            itemSubcategoryBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return ViewHolder(
            ItemSubcategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun getItemCount(): Int = subcategories?.size ?: 0

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val subcategory = subcategories!![position]
        holder.bind(subcategory)
        subcategoryClicked?.let { subcategoryClicked ->
            holder.itemView.setOnClickListener {
                subcategoryClicked.invoke(position, subcategory, subcategories)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindSubcategories(subcategories: List<Subcategory>) {
        this.subcategories = subcategories
        notifyDataSetChanged()
    }

    var subcategoryClicked: (
        (
            position: Int,
            category: Subcategory,
            subcategoriesList: List<Subcategory>?,
        ) -> Unit
    )? = null
}
