package com.evernext10.marketplace.product.detail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evernext10.core.R
import com.evernext10.core.domain.model.product.Picture
import com.evernext10.core.ext.bindImageUrl
import com.evernext10.marketplace.product.detail.presentation.databinding.ItemPhotosProductBinding

class PhotosProductAdapter : ListAdapter<Picture, PhotosProductAdapter.PhotosViewHolder>(ProductDiffUtil()) {

    companion object {
        private class ProductDiffUtil : DiffUtil.ItemCallback<Picture>() {
            override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotosProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PhotosViewHolder(private val viewItem: ItemPhotosProductBinding) :
        RecyclerView.ViewHolder(viewItem.root) {
        fun bind(picture: Picture) {
            picture.let {
                with(viewItem) {
                    productImage.bindImageUrl(
                        url = it.url,
                        placeholder = R.drawable.ic_baseline_rotate_left_24,
                        errorPlaceholder = R.drawable.ic_baseline_error_24
                    )
                }
            }
        }
    }
}
