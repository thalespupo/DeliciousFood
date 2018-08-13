package com.thalespupo.deliciousfood.model

import android.os.Parcel
import android.os.Parcelable

data class Sandwich(val id: Int,
                    var name: String,
                    @Transient var ingredients: MutableList<Ingredient> = mutableListOf(),
                    var totalPrice: Double,
                    val image: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.createTypedArrayList(Ingredient),
            parcel.readDouble(),
            parcel.readString()) {
    }

    fun makePrice() {
        totalPrice = ingredients.sumByDouble { ingredient -> ingredient.price }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeTypedList(ingredients)
        parcel.writeDouble(totalPrice)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sandwich> {
        override fun createFromParcel(parcel: Parcel): Sandwich {
            return Sandwich(parcel)
        }

        override fun newArray(size: Int): Array<Sandwich?> {
            return arrayOfNulls(size)
        }
    }
}
