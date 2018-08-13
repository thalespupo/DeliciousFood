package com.thalespupo.deliciousfood.model

import com.google.gson.annotations.SerializedName

data class Order(
        val id: Int,
        @SerializedName("id_sandwich") val sandwichId: Int,
        @SerializedName("list_ingredients_extras") val ingredients_extra: IntArray?,
        val date: Long)
