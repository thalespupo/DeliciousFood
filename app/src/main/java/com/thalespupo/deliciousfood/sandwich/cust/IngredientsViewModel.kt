package com.thalespupo.deliciousfood.sandwich.cust

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Ingredient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsViewModel : ViewModel() {

    private var ingredientsList: MutableLiveData<List<Ingredient>>? = null

    fun getIngredients(): MutableLiveData<List<Ingredient>> {
        if (ingredientsList == null) {
            ingredientsList = MutableLiveData()

            requestIngredientList()
        }

        return ingredientsList!!
    }

    private fun requestIngredientList() {
        val service = ApiServiceBuilder().build()

        service.getIngredients().enqueue(object : Callback<List<Ingredient>> {
            override fun onFailure(call: Call<List<Ingredient>>?, t: Throwable?) {
                Log.e("app", "onFailure when getIngredients:${t?.message}")
            }

            override fun onResponse(call: Call<List<Ingredient>>?, response: Response<List<Ingredient>>?) {
                if (response?.isSuccessful!!) {
                    ingredientsList!!.value = response.body()!!
                }
            }
        })
    }
}
