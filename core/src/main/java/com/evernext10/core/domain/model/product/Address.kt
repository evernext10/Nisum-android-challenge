package com.evernext10.core.domain.model.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Address(
    @Json(name = "city")
    val city: Map<String, String>? = null,
    @Json(name = "state")
    val state: Map<String, String>? = null,
    @Json(name = "country")
    val country: Map<String, String>? = null
) : Parcelable
