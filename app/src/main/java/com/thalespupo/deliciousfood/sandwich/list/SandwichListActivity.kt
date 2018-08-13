package com.thalespupo.deliciousfood.sandwich.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Sandwich
import com.thalespupo.deliciousfood.order.OrderActivity
import com.thalespupo.deliciousfood.promo.PromoActivity
import com.thalespupo.deliciousfood.sandwich.SandwichActivity
import kotlinx.android.synthetic.main.activity_sandwich_list.*


class SandwichListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich_list)
        setSupportActionBar(toolbar)

        setUpRecyclerView()
        setUpViewModel()
    }

    fun setUpRecyclerView() {
        recyclerView.adapter = SandwichAdapter(this) { sandwichItem: Sandwich -> onSandwichClicked(sandwichItem) }
        recyclerView.layoutManager = LinearLayoutManager(this)
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

    private fun onSandwichClicked(sandwich: Sandwich) {
        val intent = SandwichActivity.createIntent(this, sandwich)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.getItemId()) {
            R.id.promos_item -> {
                startActivity(Intent(this, PromoActivity::class.java))
                true
            }
            R.id.orders_item -> {
                startActivity(Intent(this, OrderActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
