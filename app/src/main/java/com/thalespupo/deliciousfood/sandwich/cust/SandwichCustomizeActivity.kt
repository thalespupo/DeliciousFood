package com.thalespupo.deliciousfood.sandwich.cust

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Ingredient
import kotlinx.android.synthetic.main.activity_sandwich_customize.*

class SandwichCustomizeActivity : AppCompatActivity() {

    companion object {
        const val RETURN_VALUE = "value"
        const val INGREDIENT_ID_KEY = "ingredient_key"
    }

    private var ingredientIds: MutableList<Int> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sandwich_customize)

        btCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        btOk.setOnClickListener {
            val allIngredients = (recyclerView.adapter as IngredientAdapter).getIngredientList()
            val resultValue: HashMap<Ingredient, Int> = createIngredientsCountMap(allIngredients, ingredientIds)

            val returnIntent = Intent()
            returnIntent.putExtra(RETURN_VALUE, resultValue)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }

        setUpRecyclerView()
        setUpViewModel()

        if (savedInstanceState?.getIntArray(INGREDIENT_ID_KEY) != null) {
            ingredientIds = savedInstanceState.getIntArray(INGREDIENT_ID_KEY).toMutableList()
            (recyclerView.adapter as IngredientAdapter).updateIdList(ingredientIds)
        }
    }

    private fun createIngredientsCountMap(allIngredients: List<Ingredient>, ingredientIds: MutableList<Int>): HashMap<Ingredient, Int> {
        val map: HashMap<Ingredient, Int>? = hashMapOf()
        allIngredients.forEach { map?.put(it, 0) }

        for (ingredient in map!!.keys) {
            val count = ingredientIds.filter { id -> id == ingredient.id }.size
            map[ingredient] = count
        }

        return map
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = IngredientAdapter(this) { ingredient: Ingredient -> onAddIngredient(ingredient) }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onAddIngredient(ingredient: Ingredient) {
        ingredientIds.add(ingredient.id)
        (recyclerView.adapter as IngredientAdapter).updateIdList(ingredientIds)
    }

    private fun setUpViewModel() {
        val modelView = ViewModelProviders.of(this).get(IngredientsViewModel::class.java)

        modelView.getIngredients().observe(this, Observer<List<Ingredient>> { list ->
            list.let {
                if (it!!.isNotEmpty()) {
                    (recyclerView.adapter as IngredientAdapter).setIngredientList(it)
                }
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putIntArray(INGREDIENT_ID_KEY, ingredientIds.toIntArray())
        super.onSaveInstanceState(outState)
    }
}
