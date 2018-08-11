package com.thalespupo.deliciousfood.sandwich.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Ingredient
import com.thalespupo.deliciousfood.model.Sandwich
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SandwichListViewModel : ViewModel() {

    private var sandwichList: MutableLiveData<List<Sandwich>>? = null
    private var rawList: List<Sandwich> = Collections.emptyList()
    private var sandwichCount = 0
    private var lock: Any = Any()

    fun getSandwiches(): MutableLiveData<List<Sandwich>> {
        if (sandwichList == null) {
            sandwichList = MutableLiveData()

            requestSandwichList()
        }

        return sandwichList!!
    }

    private fun requestSandwichList() {
        val service = ApiServiceBuilder().build()

        service.getSandwiches().enqueue(object : Callback<List<Sandwich>> {
            override fun onFailure(call: Call<List<Sandwich>>?, t: Throwable?) {
                Log.e("app", "onFailure when getSandwiches:${t?.message}")
            }

            override fun onResponse(call: Call<List<Sandwich>>?, response: Response<List<Sandwich>>?) {
                if (response?.isSuccessful!!) {

                    rawList = response.body()!!

                    sandwichCount = rawList.size

                    rawList.forEach {
                        requestIngredientsListForSandwich(it.id)
                    }
                }
            }
        })
    }

    private fun requestIngredientsListForSandwich(id: Int) {
        val service = ApiServiceBuilder().build()


        service.getIngredientsForSandwich(id).enqueue(object : Callback<List<Ingredient>> {
            override fun onFailure(call: Call<List<Ingredient>>?, t: Throwable?) {
                Log.e("app", "onFailure when getIngredientsForSandwich:${t?.message}")
            }

            override fun onResponse(call: Call<List<Ingredient>>?, response: Response<List<Ingredient>>?) {
                if (response?.isSuccessful!!) {
                    rawList.first { sandwich -> sandwich.id == id }.ingredients = response.body()!!

                    sandwichCount--
                    if (sandwichCount == 0) {
                        rawList.forEach { it.makePrice() }
                        sandwichList!!.value = rawList
                    }
                }
            }
        })
    }
}