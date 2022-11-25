package com.evernext10.marketplace.product.list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evernext10.core.R
import com.evernext10.core.domain.model.product.Product
import com.evernext10.core.ext.bindImageUrl
import com.evernext10.core.ext.toCreditQuotes
import com.evernext10.core.ext.toFormattedNumber
import com.evernext10.marketplace.product.list.presentation.databinding.ItemProductBinding

class ProductListAdapter(
    private val onClick: (Product) -> Unit
) : ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductDiffUtil()) {

    companion object {
        private class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
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

    inner class ProductViewHolder(private val viewItem: ItemProductBinding, val onClick: (Product) -> Unit) :
        RecyclerView.ViewHolder(viewItem.root) {
        fun bind(product: Product) {
            product.let {
                with(viewItem) {
                    productName.text = it.title
                    productPrice.text = it.price?.toFormattedNumber()
                    productInstallments.text = it.price?.toCreditQuotes()
                    productImage.bindImageUrl(
                        url = it.thumbnail,
                        placeholder = R.drawable.ic_baseline_rotate_left_24,
                        errorPlaceholder = R.drawable.ic_baseline_error_24
                    )
                    this.root.setOnClickListener { view ->
                        onClick(it)
                    }
                }
            }
        }
    }
}
