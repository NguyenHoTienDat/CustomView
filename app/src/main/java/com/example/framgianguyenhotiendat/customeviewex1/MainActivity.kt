package com.example.framgianguyenhotiendat.customeviewex1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_down_padding.setOnClickListener {  }
        button_up_padding.setOnClickListener { }

        val view: MyCustomView = findViewById(R.id.view_custom)
        button_swap_color.setOnClickListener { view.swapColor() }
    }
}
