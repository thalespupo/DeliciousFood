package com.thalespupo.deliciousfood.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiServiceBuilder {

    fun build() : ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ApiService::class.java)
        return service
    }
}