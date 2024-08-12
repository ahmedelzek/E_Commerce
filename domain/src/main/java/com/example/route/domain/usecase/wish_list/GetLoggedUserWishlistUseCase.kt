package com.example.route.domain.usecase.wish_list

import com.example.route.domain.common.Resource
import com.example.route.domain.contract.wish_list.WishListRepo
import com.example.route.domain.model.WishlistItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedUserWishlistUseCase
    @Inject
    constructor(
        private val repo: WishListRepo,
    ) {
        suspend operator fun invoke(token: String): Flow<Resource<List<WishlistItem>?>> {
            return repo.getLoggedUserWishlist(token)
        }
    }
