package com.thalespupo.deliciousfood.order

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Order
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = OrderAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpViewModel() {
        val modelView = ViewModelProviders.of(this).get(OrderViewModel::class.java)

        modelView.getOrders().observe(this, Observer<List<Order>> { list ->
            list.let {
                if (it!!.isNotEmpty()) {
                    (recyclerView.adapter as OrderAdapter).setOrderList(it)
                }
            }
        })
    }
}
