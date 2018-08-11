package com.thalespupo.deliciousfood.sandwich.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Sandwich
import com.thalespupo.deliciousfood.util.formatIngredients
import com.thalespupo.deliciousfood.util.loadImage
import kotlinx.android.synthetic.main.sandwich_list_item.view.*
import java.util.*

class SandwichAdapter(
        private val context: Context,
        private val clickListener: (Sandwich) -> Unit) : RecyclerView.Adapter<SandwichAdapter.ViewHolder>() {

    private var sandwichList: List<Sandwich> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
                R.layout.sandwich_list_item,
                parent,
                false
        )

        return ViewHolder(view)
    }

    override fun getItemCount() = sandwichList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sandwich = sandwichList[position]
        holder.bindView(sandwich, clickListener)
    }

    fun setSandwichList(list: List<Sandwich>) {
        sandwichList = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Sandwich, clickListener: (Sandwich) -> Unit) = with(itemView) {

            imageView.loadImage(item.image)
            tvId.text = item.id.toString()
            tvName.text = item.name
            tvPrice.text = item.totalPrice.toString()
            tvIngredients.text = item.ingredients.formatIngredients(context)

            setOnClickListener {
                clickListener(item)
            }
        }
    }
}
