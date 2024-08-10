package com.example.route.e_commerce.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.route.e_commerce.R
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun View.hideKeyboard(activity: AppCompatActivity?) {
    val inputMethodManager =
        activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.hideKeyboard(activity as AppCompatActivity?)
}

fun Activity.hideKeyboard() {
    val inputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = currentFocus
    if (currentFocusView != null) {
        inputMethodManager.hideSoftInputFromWindow(
            currentFocusView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS,
        )
    }
}
fun Fragment.showSnackBar(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
        .setTextColor(requireContext().getColor(R.color.gray))
        .setBackgroundTint(requireContext().getColor(R.color.white))
        .show()
}

fun String.formatNumber(): String {
    val symbols = DecimalFormatSymbols(Locale.ENGLISH)
    val decimalFormat = DecimalFormat("##,###,####", symbols)
    return decimalFormat.format(this.toInt())
}
