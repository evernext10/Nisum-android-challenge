package com.evernext10.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Represents an app screen which the user can navigate to
 */
sealed class Destination() : Parcelable {
    @Parcelize
    object ProductList : Destination()

    @Parcelize
    class ProductDetail(val productId: String) : Destination()

    /**
     * Extends this when origin screen must be known.
     */
    abstract class DestinationWithOrigin(val origin: String) : Destination()

    @Parcelize
    object NotificationSecondLevel : DestinationWithOrigin("notifs")
}
