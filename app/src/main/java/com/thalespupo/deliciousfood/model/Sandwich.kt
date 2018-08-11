package com.thalespupo.deliciousfood.model

import java.util.*

data class Sandwich(val id: Int,
                    val name: String,
                    @Transient var ingredients: List<Ingredient> = Collections.emptyList(),
                    var totalPrice: Double,
                    val image: String) {

    fun makePrice() {
        totalPrice = ingredients.sumByDouble { ingredient -> ingredient.price }
    }
}
