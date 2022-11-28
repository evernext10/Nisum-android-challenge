package com.evernext10.marketplace.product.list.data.repository

import android.content.Context
import android.util.Log
import com.evernext10.core.app.isNetworkAvailable
import com.evernext10.core.data.local.dao.PokemonDao
import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import com.evernext10.core.domain.network.Either
import com.evernext10.core.domain.network.Failure
import com.evernext10.core.domain.network.request
import com.evernext10.marketplace.product.list.data.di.ApiServiceModule
import com.evernext10.marketplace.product.list.domain.repository.MarketplaceProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MarketplaceProductListRepositoryImpl(
    private val api: ApiServiceModule,
    private val context: Context,
    private val pokemonDao: PokemonDao
) : MarketplaceProductListRepository {
    override suspend fun productList(
        offset: Int,
        limit: Int
    ): Either<Failure, MarketplacePokemonListResponse> {
        return when (context.isNetworkAvailable()) {
            true -> {
                request(
                    api.getProductsBySearch(offset, limit),
                    { it },
                    MarketplacePokemonListResponse(),
                    pokemonDao
                )
            }
            false -> {
                // check in db if the data exists
                val data = getPokemonDataFromDatabase()
                return if (data.isNotEmpty()) {
                    Log.d("Database_room", "from db")
                    Either.Right(
                        MarketplacePokemonListResponse(
                            results = data
                        )
                    )
                } else {
                    // no network
                    Either.Left(Failure.NetworkConnection)
                }
            }
        }
    }

    private suspend fun getPokemonDataFromDatabase(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            pokemonDao.findAllPokemons()
        }
    }
}
