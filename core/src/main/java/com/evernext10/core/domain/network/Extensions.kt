package com.evernext10.core.domain.network

import com.evernext10.core.data.local.dao.PokemonDao
import com.evernext10.core.domain.model.pokemon.response.MarketplacePokemonListResponse
import retrofit2.Call

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T,
    pokemonDao: PokemonDao
): Either<Failure, R> {
    return try {
        Either.Left(Failure.ServerError)
        val response = call.execute()
        when (response.isSuccessful) {
            true -> {
                pokemonDao.insertPokemonList((transform((response.body() ?: default)) as MarketplacePokemonListResponse).results)
                Either.Right(transform((response.body() ?: default)))
            }
            false -> Either.Left(Failure.ServerError)
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.ServerError)
    }
}
