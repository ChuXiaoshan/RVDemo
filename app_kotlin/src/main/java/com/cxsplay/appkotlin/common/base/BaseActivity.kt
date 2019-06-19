package com.cxsplay.appkotlin.common.base

import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * Created by CxS on 2019/5/24 17:27.
 * Description:
 */
abstract class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindowAnimations()
    }

    private fun setupWindowAnimations() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            val slide = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide)
//            window.enterTransition = slide

//            val fade = TransitionInflater.from(this).inflateTransition(R.transition.activity_fade)
//            window.exitTransition = fade

            val slide = Slide()
            slide.duration = 100
            window.returnTransition = slide
            window.enterTransition = slide

            window.exitTransition


            val fade = Fade()
            fade.duration = 300

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}