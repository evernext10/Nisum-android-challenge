package com.evernext10.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.evernext10.core.domain.model.pokemon.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonList(movies: List<Pokemon>)

    @Update
    fun updatePokemon(movie: Pokemon)

    @Query("SELECT * FROM POKEMON WHERE id = :id_")
    fun getPokemon(id_: Int): Pokemon

    @Query("SELECT * FROM POKEMON")
    fun findAllPokemons(): List<Pokemon>
}
