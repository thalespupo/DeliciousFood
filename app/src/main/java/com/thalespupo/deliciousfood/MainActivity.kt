package com.thalespupo.deliciousfood

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thalespupo.deliciousfood.sandwich.list.SandwichListActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, SandwichListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
