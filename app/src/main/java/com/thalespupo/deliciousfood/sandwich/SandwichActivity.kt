package com.thalespupo.deliciousfood.sandwich

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.api.RequestBodySandwichCustomized
import com.thalespupo.deliciousfood.model.Ingredient
import com.thalespupo.deliciousfood.model.Sandwich
import com.thalespupo.deliciousfood.sandwich.cust.SandwichCustomizeActivity
import com.thalespupo.deliciousfood.sandwich.cust.SandwichCustomizeActivity.Companion.RETURN_VALUE
import com.thalespupo.deliciousfood.util.formatIngredients
import com.thalespupo.deliciousfood.util.loadImage
import kotlinx.android.synthetic.main.activity_sandwich.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SandwichActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE = 1
        private const val SANDWICH_KEY = "SANDWICH"
        fun createIntent(context: Context, sandwich: Sandwich): Intent {
            val intent = Intent(context, SandwichActivity::class.java)
            intent.putExtra(SANDWICH_KEY, sandwich)
            return intent
        }
    }

    private var ingredientsOriginal: List<Ingredient> = Collections.emptyList()
    private var ingredientsExtra: HashMap<Ingredient, Int> = hashMapOf()
    private val callback = object : Callback<Sandwich> {
        override fun onFailure(call: Call<Sandwich>?, t: Throwable?) {
            Log.e("app", "onFailure when putSandwich:${t?.message}")
        }

        override fun onResponse(call: Call<Sandwich>?, response: Response<Sandwich>?) {
            Toast.makeText(baseContext, R.string.order_put_ok, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich)
        setSupportActionBar(toolbar)

        val sandwich = intent.extras.get(SANDWICH_KEY) as Sandwich
        ingredientsOriginal = sandwich.ingredients

        setupViewModel(sandwich)

        fabPutOrder.setOnClickListener { _ ->

            if (ingredientsExtra.values.sum() == 0) {
                ApiServiceBuilder().build().putSandwich(sandwich.id).enqueue(callback)
            } else {
                val idList: MutableList<Int> = mutableListOf()

                for (ingredient in ingredientsExtra.keys) {
                    val count = ingredientsExtra[ingredient]!!
                    for (i in 0 until count) {
                        idList.add(ingredient.id)
                    }
                }

                ApiServiceBuilder().build().putSandwichCustomized(
                        sandwich.id,
                        RequestBodySandwichCustomized(idList.toIntArray())
                ).enqueue(callback)
            }
        }

        fabCustomize.setOnClickListener { _ ->
            val intent = Intent(this, SandwichCustomizeActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    private fun setupViewModel(sandwichFromIntent: Sandwich) {
        val modelView = ViewModelProviders.of(this).get(SandwichViewModel::class.java)

        val mutableLiveData = modelView.getSandwich()
        mutableLiveData.observe(this, Observer<Sandwich> { sandwich ->
            if (sandwich != null) {
                fillView(sandwich)
            }
        })

        mutableLiveData.value = sandwichFromIntent
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()

                @Suppress("UNCHECKED_CAST")
                val returnValue = data?.getSerializableExtra(RETURN_VALUE) as HashMap<Ingredient, Int>

                val modelView = ViewModelProviders.of(this).get(SandwichViewModel::class.java)
                val mutableLiveData = modelView.getSandwich()
                val sandwich = mutableLiveData.value

                if (sandwich != null) {
                    ingredientsExtra = returnValue
                    for (ingredient in ingredientsExtra.keys) {
                        val count = ingredientsExtra[ingredient]
                        for (i in 1..count!!) {
                            sandwich.ingredients.add(ingredient)
                        }
                    }

                    sandwich.name +=" - do seu jeito"
                    sandwich.makePrice()

                    mutableLiveData.value = sandwich
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fillView(sandwich: Sandwich) {
        imageView.loadImage(applicationContext, sandwich.image)
        tvName.text = sandwich.name
        tvPrice.text = sandwich.totalPrice.toString()
        tvIngredients.text = sandwich.ingredients.formatIngredients(this)
    }
}
