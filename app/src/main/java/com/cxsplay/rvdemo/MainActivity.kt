package com.cxsplay.rvdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btn1.setOnClickListener {
            startActivity(Intent(this, RVActivity::class.java))
//            Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show()
        }
    }
}
