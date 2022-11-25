package com.evernext10.navigation

import android.os.Parcelable
import com.evernext10.core.domain.model.pokemon.Pokemon
import kotlinx.android.parcel.Parcelize

/**
 * Represents an app screen which the user can navigate to
 */
sealed class Destination() : Parcelable {
    @Parcelize
    object PockemonList : Destination()

    @Parcelize
    class PockemonDetail(val pockemon: Pokemon) : Destination()

}
