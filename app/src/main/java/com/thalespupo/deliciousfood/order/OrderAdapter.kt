package com.thalespupo.deliciousfood.order

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Order
import kotlinx.android.synthetic.main.order_list_item.view.*
import java.util.*

class OrderAdapter(private val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private var orderList: List<Order> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
                R.layout.order_list_item,
                parent,
                false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(orderList[position])
    }

    fun setOrderList(it: List<Order>) {
        orderList = it
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Order) = with(itemView) {
            tvId.text = item.id.toString()
            tvSandwich.text = item.sandwichId.toString()
            if (item.ingredients_extra != null) {
                tvIngredients.text = Arrays.toString(item.ingredients_extra)
            }
            tvDate.text = item.date.toString()

        }
    }
}
