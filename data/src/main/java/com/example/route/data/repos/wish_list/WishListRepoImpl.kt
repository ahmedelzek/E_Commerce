package com.example.route.data.repos.wish_list

import com.example.route.data.contract.WishListOnlineDataSource
import com.example.route.data.toFlow
import com.example.route.domain.common.Resource
import com.example.route.domain.contract.wish_list.WishListRepo
import com.example.route.domain.model.WishlistItem
import com.example.route.domain.model.WishlistResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WishListRepoImpl
    @Inject
    constructor(
        private val wishlistOnlineDataSource: WishListOnlineDataSource,
    ) : WishListRepo {
        override suspend fun addProductToWishlist(
            token: String,
            productId: String,
        ): Flow<Resource<WishlistResponse<List<String>?>>> {
            return toFlow {
                wishlistOnlineDataSource.addProductToWishlist(token, productId)
            }
        }

        override suspend fun deleteProductFromWishlist(
            token: String,
            productId: String,
        ): Flow<Resource<WishlistResponse<List<String>?>>> {
            return toFlow { wishlistOnlineDataSource.deleteProductFromWishlist(token, productId) }
        }

        override suspend fun getLoggedUserWishlist(token: String): Flow<Resource<List<WishlistItem>?>> {
            return toFlow {
                wishlistOnlineDataSource.getLoggedUserWishlist(token)
            }
        }
    }
