package com.thalespupo.deliciousfood.sandwich

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.thalespupo.deliciousfood.model.Sandwich

class SandwichViewModel : ViewModel() {

    private var currentSandwich: MutableLiveData<Sandwich>? = null

    fun getSandwich(): MutableLiveData<Sandwich> {
        if (currentSandwich == null) {
            currentSandwich = MutableLiveData()
        }

        return currentSandwich!!
    }


}