package com.thalespupo.deliciousfood.order

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel : ViewModel() {

    private var orderList: MutableLiveData<List<Order>>? = null

    fun getOrders(): MutableLiveData<List<Order>> {
        if (orderList == null) {
            orderList = MutableLiveData()

            requestOrderList()
        }

        return orderList!!
    }

    private fun requestOrderList() {
        ApiServiceBuilder().build().getOrders().enqueue(object : Callback<List<Order>> {
            override fun onFailure(call: Call<List<Order>>?, t: Throwable?) {
                Log.e("app", "onFailure when getOrders:${t?.message}")
            }

            override fun onResponse(call: Call<List<Order>>?, response: Response<List<Order>>?) {
                if (response?.isSuccessful!!) {
                    orderList!!.value = response.body()!!
                }
            }
        })
    }
}
