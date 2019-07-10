package com.cxsplay.rvdemo.pickerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.rvdemo.R
import com.cxsplay.rvdemo.common.expand.click
import kotlinx.android.synthetic.main.activity_picker_view.*
import java.util.*


class PickerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker_view)
        init()
    }

    private fun init() {
        btn_date1.click {
            showDate1()
        }
    }

    private fun showDate1() {
        val selectedDate = Calendar.getInstance()
        val startDate = Calendar.getInstance()
        startDate.set(2015, 1, 1)
        val endDate = Calendar.getInstance()
        TimePickerBuilder(this,
            OnTimeSelectListener { date, _ ->
                ToastUtils.showShort("--->$date")
                ToastUtils.showShort("--->$date")
            })
            .setType(booleanArrayOf(true, false, false, true, true, false))
            .setSubmitColor(ColorUtils.getColor(R.color.colorAccent))
            .setCancelColor(ColorUtils.getColor(R.color.colorAccent))
            .setRangDate(startDate, endDate)
            .setDate(selectedDate)
            .setTitleText("选择开始时间")
            .build().show()
    }
}
