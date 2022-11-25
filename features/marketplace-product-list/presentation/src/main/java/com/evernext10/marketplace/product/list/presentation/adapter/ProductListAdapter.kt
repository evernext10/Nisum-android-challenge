package com.evernext10.marketplace.product.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evernext10.core.R
import com.evernext10.core.domain.model.pokemon.Pokemon
import com.evernext10.core.ext.*
import com.evernext10.marketplace.product.list.presentation.databinding.ItemProductBinding

class ProductListAdapter(
    private val onClick: (Pokemon) -> Unit
) : ListAdapter<Pokemon, ProductListAdapter.ProductViewHolder>(ProductDiffUtil()) {

    companion object {
        private class ProductDiffUtil : DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductViewHolder(private val viewItem: ItemProductBinding, val onClick: (Pokemon) -> Unit) :
        RecyclerView.ViewHolder(viewItem.root) {
        fun bind(product: Pokemon) {
            product.let {
                with(viewItem) {
                    pokemonItemTitle.text = it.name
                    pokemonItemImage.bindImageUrl(
                        url = it.url?.pokemonImageUrl(),
                        placeholder = R.drawable.ic_baseline_rotate_left_24,
                        errorPlaceholder = R.drawable.ic_baseline_error_24
                    )
                    this.root.setOnClickListener { view ->
                        it.id = it.url?.getPokemonId()?.takeLast(3)?.toInt()
                        onClick(it)
                    }
                }
            }
        }
    }
}
