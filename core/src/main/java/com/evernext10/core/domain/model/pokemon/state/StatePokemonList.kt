package com.evernext10.core.domain.model.pokemon.state

import com.evernext10.core.domain.model.pokemon.Pokemon

sealed class StatePokemonList {
    object Loading : StatePokemonList()
    data class Success(val pokemon: List<Pokemon>) : StatePokemonList()
    object Unauthorized : StatePokemonList()
    object Error : StatePokemonList()
}
