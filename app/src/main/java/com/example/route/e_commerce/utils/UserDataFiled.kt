package com.example.route.e_commerce.utils

import com.example.route.e_commerce.Constants

enum class UserDataFiled(val value: String) {
    TOKEN(Constants.USER_TOKEN),
    ROLE(Constants.USER_ROLE),
    NAME(Constants.USER_NAME),
    EMAIL(Constants.USER_EMAIL),
    CART_ITEM_COUNT(Constants.CART_ITEM_COUNT),
}
