package com.example.route.domain.usecase.wish_list

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.wish_list.WishListRepo
import com.example.route.domain.model.WishlistResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteProductFromWishlistUseCase
    @Inject
    constructor(
        private val repo: WishListRepo,
    ) {
        suspend operator fun invoke(
            token: String,
            productId: String,
        ): Flow<Resource<WishlistResponse<List<String>?>>> {
            return repo.deleteProductFromWishlist(token, productId)
        }
    }
