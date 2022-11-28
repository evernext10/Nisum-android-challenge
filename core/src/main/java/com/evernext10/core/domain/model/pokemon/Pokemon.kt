package com.evernext10.core.domain.model.pokemon

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
@Entity(primaryKeys = [("name")])
data class Pokemon(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "url")
    val url: String? = null
) : Parcelable
