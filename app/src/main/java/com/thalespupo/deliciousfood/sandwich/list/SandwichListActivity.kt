package com.thalespupo.deliciousfood.sandwich.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Sandwich
import kotlinx.android.synthetic.main.activity_sandwich_list.*

class SandwichListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich_list)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        val modelView = ViewModelProviders.of(this).get(SandwichListViewModel::class.java)

        modelView.getSandwiches().observe(this, Observer<List<Sandwich>> { list ->
            list.let {
                if (it!!.isNotEmpty()) {
                    (recyclerView.adapter as SandwichAdapter).setSandwichList(it)
                }
            }
        })
    }

    fun setUpRecyclerView() {
        recyclerView.adapter = SandwichAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}
