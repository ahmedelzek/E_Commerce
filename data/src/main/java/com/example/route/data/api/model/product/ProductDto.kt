package com.example.route.data.api.model.product

import com.example.route.data.api.model.brand.BrandDto
import com.example.route.data.api.model.categroy.CategoryDto
import com.example.route.domain.model.Product


data class ProductDto(
	val sold: Int? = null,
	val images: List<String?>? = null,
	val quantity: Int? = null,
	val imageCover: String? = null,
	val description: String? = null,
	val title: String? = null,
	val ratingsQuantity: Int? = null,
	val ratingsAverage: Double? = null,
	val price: Int? = null,
	val id: String? = null,
	val subcategory: List<CategoryDto?>? = null,
	val category: CategoryDto? = null,
	val brand: BrandDto? = null,
	val slug: String? = null,
) {
    fun toProduct(): Product {
		return Product(
			sold,
			images,
			quantity,
			imageCover,
			description,
			title,
			ratingsQuantity,
			ratingsAverage,
			price = price,
			id = id,
			subcategory = subcategory,
			category = category?.toCategory(),
			brand = brand?.toBrand(),
			slug = slug
		)
    }
}