package com.thalespupo.deliciousfood.util

import android.content.Context
import com.thalespupo.deliciousfood.R
import com.thalespupo.deliciousfood.model.Ingredient

fun List<Ingredient>.formatIngredients(context: Context): String {
    var result = ""
    for (i in 0 until this.size) {
        result += if (i == this.size -1) {
            "${context.getString(R.string.and)} ${this[i].name}."
        } else {
            "${this[i].name}, "
        }
    }
    return result
}
