package com.example.route.e_commerce.utils


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.route.e_commerce.R
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {

        @BindingAdapter("app:urlCircular")
        @JvmStatic
        fun bindImageCircular(imageView: ImageView, url: String?) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_category_placeholder)
                .transform(TransformCircular())
                .into(imageView)

        }

        @BindingAdapter("app:url")
        @JvmStatic
        fun bindImage(imageView: ImageView, url: String?) {
            Picasso.get()
                .load(url)
                .centerCrop()
                .fit()
                .into(imageView)
        }

    }
}
@BindingAdapter("app:error")
fun setErrorTextInputLayout(textInputLayout: TextInputLayout, errorText: String?) {
    textInputLayout.error = errorText
}