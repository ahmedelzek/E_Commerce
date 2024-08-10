package com.example.route.domain.contract.product

enum class SortBy(val value: String) {
    PRICE_ASC("price"),
    PRICE_DESC("-price"),
    MOST_SELLING("-sold"),
}