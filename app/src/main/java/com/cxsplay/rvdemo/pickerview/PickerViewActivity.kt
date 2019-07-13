package com.cxsplay.rvdemo.pickerview

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.rvdemo.R
import com.cxsplay.rvdemo.common.expand.click
import com.cxsplay.rvdemo.databinding.ActivityPickerViewBinding
import com.cxsplay.rvdemo.databinding.ItemTagBinding
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_picker_view.*
import java.text.SimpleDateFormat
import java.util.*


class PickerViewActivity : AppCompatActivity() {

    private lateinit var bind: ActivityPickerViewBinding

    private var startTime: String? = null
    private var endTime: String? = null
    private var fullTime: String? = null

    private val timePickerView: TimePickerView by lazy {
        TimePickerBuilder(this, timeSelectListener)
            .setType(booleanArrayOf(false, false, false, true, true, false))
            .setSubmitColor(ColorUtils.getColor(R.color.colorAccent))
            .setCancelColor(ColorUtils.getColor(R.color.colorAccent))
            .setGravity(Gravity.CENTER)
            .setTitleText("选择开始时间")
            .isCyclic(true)
            .setTitleSize(14)
            .build()
    }

    private val timeSelectListener: OnTimeSelectListener by lazy {
        OnTimeSelectListener { date, _ ->
            val time = TimeUtils.date2String(date, SimpleDateFormat("HH:mm", Locale.US))
            if (startTime.isNullOrEmpty()) {
                startTime = time
                tv_show.postDelayed({
                    timePickerView.setTitleText("选择结束时间")
                    timePickerView.show()
                }, 200)
            } else {
                endTime = time
            }
            if (!startTime.isNullOrEmpty() && !endTime.isNullOrEmpty()) {
                fullTime = "$startTime-$endTime"
                startTime = null
                endTime = null
                ToastUtils.showShort("$fullTime")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_picker_view)
        init()
    }

    private fun init() {
        btn_date1.click {
            timePickerView.setTitleText("选择开始时间")
            timePickerView.show()
        }

        val strs = mutableListOf("12:34-56:78", "12:34-56:78", "12:34-56:78", "12:34-56:78", "12:34-56:78", "12:34-56:78")

        bind.tfl.adapter = object : TagAdapter<String>(strs) {

            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tagBind: ItemTagBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_tag, null, false)
                tagBind.tvTag.text = t
                tagBind.ivTag.click {
                    ToastUtils.showShort(position)
                    strs.removeAt(position)
                    notifyDataChanged()
                }
                return tagBind.root
            }
        }

        btn_city.click {

        }
    }

    private fun initCity() {

    }
}
