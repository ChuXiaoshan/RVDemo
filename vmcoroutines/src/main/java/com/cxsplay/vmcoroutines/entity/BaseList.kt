package com.cxsplay.appkotlin.entity

/**
 * Created by CxS on 2019/5/23 15:47.
 * Description:
 */
class BaseList<T> {

    var page: Int = 0
    var size: Int = 0
    var totalPage: Long = 0
    var totalSize: Long = 0
    var data: List<T>? = null

    override fun toString(): String {
        return "PageInfo{" +
                "pageBean=" + page +
                ", size=" + size +
                ", totalPage=" + totalPage +
                ", totalSize=" + totalSize +
                ", data=" + data +
                '}'.toString()
    }
}
