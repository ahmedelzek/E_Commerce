package com.example.route.data.api.model

import com.example.route.data.api.model.categroy.Pagination
import com.google.gson.annotations.SerializedName

data class Response<T>(

    @field:SerializedName("metadata")
    val pagination: Pagination? = null,

    @field:SerializedName("data")
    val data: T? = null,

    @field:SerializedName("results")
    val results: Int? = null,

    @field:SerializedName("statusMessage")
    val statusMessage: String? = null,

    @field:SerializedName("message")
    val message: String? = null

)