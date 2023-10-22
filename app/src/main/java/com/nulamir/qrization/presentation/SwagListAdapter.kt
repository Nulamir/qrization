package com.nulamir.qrization.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.nulamir.qrization.domain.SwagItem
import com.nulamir.qrization.R


class SwagListAdapter : ListAdapter<SwagItem, SwagItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((SwagItem) -> Unit)? = null
    var onShopItemClickListener: ((SwagItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwagItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        val viewHolder = SwagItemViewHolder(view)
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(getItem(viewHolder.adapterPosition))
            true
        }
        viewHolder.view.setOnClickListener {
            onShopItemClickListener?.invoke(getItem(viewHolder.adapterPosition))
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 30
    }
}
