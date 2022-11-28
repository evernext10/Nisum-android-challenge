package com.evernext10.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.evernext10.core.data.local.converters.ListConverters
import com.evernext10.core.data.local.dao.PokemonDao
import com.evernext10.core.domain.model.pokemon.Pokemon

@Database(
    entities = [(Pokemon::class)],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [(ListConverters::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
