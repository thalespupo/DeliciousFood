package com.thalespupo.deliciousfood.sandwich.cust

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Ingredient
import com.thalespupo.deliciousfood.util.loadImage
import kotlinx.android.synthetic.main.ingredient_list_item.view.*
import java.util.*

class IngredientAdapter(
        private val context: Context,
        private val clickListener: (Ingredient) -> Unit) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    private var ingredientList: List<Ingredient> = Collections.emptyList()
    private var ids: List<Int> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
                R.layout.ingredient_list_item,
                parent,
                false
        )

        return IngredientAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int = ingredientList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.bindView(ingredient, clickListener, ids)
    }

    fun setIngredientList(list: List<Ingredient>) {
        ingredientList = list
        notifyDataSetChanged()
    }

    fun getIngredientList(): List<Ingredient> {
        return ingredientList
    }

    fun updateIdList(ids: MutableList<Int>) {
        this.ids = ids
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: Ingredient, clickListener: (Ingredient) -> Unit, ids: List<Int>) = with(itemView) {

            imageView.loadImage(context.applicationContext, item.image)
            tvName.text = item.name
            tvAmount.text = ids.filter { id -> id == item.id }.size.toString()

            ivAdd.setOnClickListener {
                clickListener(item)
            }
        }
    }
}
