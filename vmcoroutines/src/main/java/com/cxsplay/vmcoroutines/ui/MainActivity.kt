package com.cxsplay.vmcoroutines.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cxsplay.vmcoroutines.R
import com.cxsplay.vmcoroutines.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        bind.btnList.setOnClickListener {
            startActivity(Intent(this, ProductsActivity::class.java))
        }
    }
}
