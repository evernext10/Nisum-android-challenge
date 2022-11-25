package com.evernext10.core.ext

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.evernext10.core.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun View.visible(isVisible: Boolean, isWithAnimation: Boolean = false) {
    val currentAlpha = alpha
    if (!isVisible && isWithAnimation) {
        animate()
            .alpha(0f)
            .setDuration(1500L)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = View.GONE
                    alpha = currentAlpha
                }
            })
    } else {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}

fun ImageView.bindImageUrl(
    url: String?,
    @DrawableRes placeholder: Int,
    @DrawableRes errorPlaceholder: Int
) {
    if (url.isNullOrBlank()) {
        setImageResource(placeholder)
        return
    }

    Glide.with(context)
        .load(url)
        .error(errorPlaceholder)
        .placeholder(placeholder)
        .into(this)
}

inline fun SearchView.onQueryTextChanged(crossinline onQueryTextChanged: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextChanged(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

fun Fragment.showAlertDialogErrorApi(controller: NavController? = null) {
    MaterialAlertDialogBuilder(this.requireContext())
        .setCancelable(false)
        .setTitle(this.resources.getString(R.string.error_api_generic_title))
        .setMessage(this.resources.getString(R.string.error_api_generic_message))
        .setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
            controller?.popBackStack()
        }.show()
}

fun Long.toFormattedNumber() = "$ ${String.format("%,d", this)}"

fun Long.toCreditQuotes() = "en 36x $ ${String.format("%,d", this/36)}"

fun Double.toFormattedNumber() = "$ ${String.format("%,d", this.toLong())}"

fun String.getPokemonId() = this.filter { it.isDigit() }

fun String.pokemonImageUrl() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonId().takeLast(3)}.png"