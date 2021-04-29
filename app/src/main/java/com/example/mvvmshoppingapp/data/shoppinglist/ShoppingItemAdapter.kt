package com.example.mvvmshoppingapp.data.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmshoppingapp.R
import com.example.mvvmshoppingapp.data.entities.ShoppingItem
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.tvName.text = currentItem.name
        holder.itemView.tvAmount.text = currentItem.amount.toString()
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(currentItem)
        }
        holder.itemView.ivPlus.setOnClickListener {
            currentItem.amount++
            viewModel.upsert(currentItem)
        }
        holder.itemView.ivMinus.setOnClickListener {
            if (currentItem.amount > 0) {
                currentItem.amount--
                viewModel.upsert(currentItem)
            } else {
                viewModel.delete(currentItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}
