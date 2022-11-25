package com.evernext10.core.domain.model.product

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Product(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "price")
    val price: Long? = null,
    @Json(name = "currency_id")
    val currencyId: String? = null,
    @Json(name = "available_quantity")
    val availableQuantity: Int? = null,
    @Json(name = "sold_quantity")
    val soldQuantity: Int? = 0,
    @Json(name = "thumbnail")
    val thumbnail: String? = null,
    @Json(name = "condition")
    val condition: String? = null,
    @Json(name = "sold")
    val sold: Int? = null,
    @Json(name = "seller_address")
    val address: Address? = null,
    @Json(name = "shipping")
    val shipping: Shipping? = null,
    @Json(name = "pictures")
    val pictures: List<Picture>? = null
) : Parcelable
