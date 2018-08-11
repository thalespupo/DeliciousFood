package com.thalespupo.deliciousfood.util

import android.content.Context
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Ingredient

fun List<Ingredient>.formatIngredients(context: Context): String {
    var result = ""
    this.forEach { it ->
        result += if (this.last() == it) {
            "${context.resources.getString(R.string.and)} ${it.name}."
        } else {
            "${it.name}, "
        }
    }
    return result
}
