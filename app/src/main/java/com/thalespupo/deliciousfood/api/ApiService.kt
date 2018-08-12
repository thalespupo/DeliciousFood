package com.thalespupo.deliciousfood.api

import com.thalespupo.deliciousfood.model.Ingredient
import com.thalespupo.deliciousfood.model.Order
import com.thalespupo.deliciousfood.model.Promo
import com.thalespupo.deliciousfood.model.Sandwich
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("lanche")
    fun getSandwiches(): Call<List<Sandwich>>

    @GET("ingrediente/de/{id}")
    fun getIngredientsForSandwich(
            @Path("id") sandwichId: Int): Call<List<Ingredient>>

    @GET("lanche/{id}")
    fun getSandwich(
            @Path("id") sandwichId: Int): Call<Sandwich>

    @GET("ingrediente")
    fun getIngredients(): Call<List<Ingredient>>

    @PUT("pedido/{id}")
    fun putSandwich(
            @Path("id") sandwichId: Int): Call<Sandwich>

    @PUT("pedido/{id}")
    fun putSandwichCustomized(
            @Path("id") sandwichId: Int,
            @Body extras: RequestBodySandwichCustomized): Call<Sandwich>

    @GET("promocao")
    fun getPromos(): Call<List<Promo>>

    @GET("pedido")
    fun getOrders(): Call<List<Order>>
}

data class RequestBodySandwichCustomized(
        val list_ingredients_extras: IntArray)
