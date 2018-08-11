package com.thalespupo.deliciousfood.sandwich

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Sandwich
import com.thalespupo.deliciousfood.util.formatIngredients
import com.thalespupo.deliciousfood.util.loadImage

import kotlinx.android.synthetic.main.activity_sandwich.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SandwichActivity : AppCompatActivity() {

    companion object {
        private const val SANDWICH_KEY = "SANDWICH"
        fun createIntent(context: Context, sandwich: Sandwich): Intent {
            val intent = Intent(context, SandwichActivity::class.java)
            intent.putExtra(SANDWICH_KEY, sandwich)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich)
        setSupportActionBar(toolbar)

        val sandwich = intent.extras.get(SANDWICH_KEY) as Sandwich

        imageView.loadImage(sandwich.image)
        tvName.text = sandwich.name
        tvPrice.text = sandwich.totalPrice.toString()
        tvIngredients.text = sandwich.ingredients.formatIngredients(this)

        fab.setOnClickListener { view ->
            ApiServiceBuilder().build().putSandwich(sandwich.id).enqueue(object: Callback<Sandwich> {
                override fun onFailure(call: Call<Sandwich>?, t: Throwable?) {
                    Log.e("app", "onFailure when putSandwich${t?.message}")
                }

                override fun onResponse(call: Call<Sandwich>?, response: Response<Sandwich>?) {
                    Toast.makeText(baseContext, "Put OK", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}
