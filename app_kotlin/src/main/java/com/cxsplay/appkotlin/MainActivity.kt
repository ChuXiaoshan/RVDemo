package com.cxsplay.appkotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cxsplay.appkotlin.databinding.ActivityMainBinding
import com.cxsplay.appkotlin.ui.AddProductActivity
import com.cxsplay.appkotlin.ui.ProductsActivity


class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        bind.btnAddProduct.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }
        bind.btnShowProduct.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
        }
    }
}
