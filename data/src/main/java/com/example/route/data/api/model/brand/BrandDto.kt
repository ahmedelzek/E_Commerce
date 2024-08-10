package com.example.route.data.api.model.brand

import com.example.route.domain.model.Brand


data class BrandDto(
	val image: String? = null,
	val name: String? = null,
	val id: String? = null,
	val slug: String? = null
) {
    fun toBrand(): Brand? {
		return Brand(
			image,
			name,
			id,
			slug)
    }
}