package com.evernext10.core.domain.model.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Shipping(
    @Json(name = "free_shipping")
    val freeShipping: Boolean? = false
) : Parcelable
