package com.thalespupo.deliciousfood.promo

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Promo

import kotlinx.android.synthetic.main.activity_promo.*

class PromoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promo)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        setUpViewModel()

    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = PromoAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpViewModel() {
        val modelView = ViewModelProviders.of(this).get(PromoViewModel::class.java)

        modelView.getPromos().observe(this, Observer<List<Promo>> { list ->
            list.let {
                if (it!!.isNotEmpty()) {
                    (recyclerView.adapter as PromoAdapter).setPromoList(it)
                }
            }
        })    }
}
