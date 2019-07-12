package com.cxsplay.rvdemo.qqui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cxsplay.rvdemo.R
import com.cxsplay.rvdemo.databinding.ActivitySectionBinding
import com.qmuiteam.qmui.widget.section.QMUISection
import com.qmuiteam.qmui.widget.section.QMUIStickySectionAdapter
import java.util.*

class SectionActivity : AppCompatActivity() {

    private lateinit var bind: ActivitySectionBinding
    private lateinit var lm: RecyclerView.LayoutManager
    private lateinit var mAdapter: QMUIStickySectionAdapter<SectionHeader, SectionItem, QMUIStickySectionAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_section)
        init()
    }

    private fun init() {
        lm = LinearLayoutManager(this)
        mAdapter = QDGridSectionAdapter()
        bind.sl.setLayoutManager(lm)
        bind.sl.setAdapter(mAdapter, true)
        val list = ArrayList<QMUISection<SectionHeader, SectionItem>>()
        for (i in 0..9) {
            list.add(createSection("header $i", false))
        }
        mAdapter.setData(list)

        mAdapter.setCallback(object : QMUIStickySectionAdapter.Callback<SectionHeader, SectionItem> {

            override fun onItemLongClick(holder: QMUIStickySectionAdapter.ViewHolder?, position: Int): Boolean {
                return true
            }

            override fun onItemClick(holder: QMUIStickySectionAdapter.ViewHolder?, position: Int) {
            }

            override fun loadMore(section: QMUISection<SectionHeader, SectionItem>?, loadMoreBefore: Boolean) {
                for (i in 0..9) {
                    list.add(createSection("header $i", false))
                }
                mAdapter.finishLoadMore(section, emptyList(), loadMoreBefore, false)
//                mAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun createSection(headerText: String, isFold: Boolean): QMUISection<SectionHeader, SectionItem> {
        val header = SectionHeader(headerText, "100笔", "￥123456")
        val contents = ArrayList<SectionItem>()
        for (i in 0..10) {
            contents.add(SectionItem("item $i", "123", "456"))
        }
        val section = QMUISection(header, contents, isFold)
        // if test load more, you can open the code
        section.isExistAfterDataToLoad = true
        return section
    }
}
