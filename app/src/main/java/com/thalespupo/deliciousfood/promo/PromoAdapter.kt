package com.thalespupo.deliciousfood.promo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Promo
import kotlinx.android.synthetic.main.promo_list_item.view.*
import java.util.*

class PromoAdapter(private val context: Context) : RecyclerView.Adapter<PromoAdapter.ViewHolder>() {

    private var promoList: List<Promo> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(
                R.layout.promo_list_item,
                parent,
                false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = promoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(promoList[position])
    }

    fun setPromoList(it: List<Promo>) {
        promoList = it
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Promo) = with(itemView) {

            tvName.text = item.name
            tvDescription.text = item.description
        }
    }
}
