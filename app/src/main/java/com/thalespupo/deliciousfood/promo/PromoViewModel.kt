package com.thalespupo.deliciousfood.promo

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.thalespupo.deliciousfood.api.ApiServiceBuilder
import com.thalespupo.deliciousfood.model.Promo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PromoViewModel : ViewModel() {

    private var promoList: MutableLiveData<List<Promo>>? = null

    fun getPromos(): MutableLiveData<List<Promo>> {
        if (promoList == null) {
            promoList = MutableLiveData()

            requestPromoList()
        }

        return promoList!!
    }

    private fun requestPromoList() {
        ApiServiceBuilder().build().getPromos().enqueue(object : Callback<List<Promo>> {
            override fun onFailure(call: Call<List<Promo>>?, t: Throwable?) {
                Log.e("app", "onFailure when getPromos:${t?.message}")
            }

            override fun onResponse(call: Call<List<Promo>>?, response: Response<List<Promo>>?) {
                if (response?.isSuccessful!!) {
                    promoList!!.value = response.body()!!
                }
            }
        })
    }
}
