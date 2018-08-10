package com.thalespupo.deliciousfood.sandwich.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Sandwich
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SandwichListViewModel: ViewModel() {
    private var sandwichList: MutableLiveData<List<Sandwich>>? = null

    fun getSandwiches() : MutableLiveData<List<Sandwich>> {
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
                Log.e("app", "onFailure:${t?.message}")
            }

            override fun onResponse(call: Call<List<Sandwich>>?, response: Response<List<Sandwich>>?) {
                if (response?.isSuccessful!!) {
                    sandwichList!!.value = response.body()
                }
            }

        })
    }


}