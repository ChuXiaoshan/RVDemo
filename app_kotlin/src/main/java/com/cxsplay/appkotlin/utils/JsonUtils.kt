package com.cxsplay.appkotlin.utils

import com.google.gson.Gson
import com.google.gson.JsonNull

import java.lang.reflect.Type

/**
 * Created by Xiaoshan on 2016/3/18.
 * gsonUtil
 */

object JsonUtils {

    private val gson = Gson()

    /**
     * 将对象转为JSON串，此方法能够满足大部分需求
     *
     * @param obj :将要被转化的对象
     * @return :转化后的JSON串
     */
    fun toJson(obj: Any?): String {
        return if (obj == null) {
            gson.toJson(JsonNull.INSTANCE)
        } else gson.toJson(obj)
    }

    /**
     * 用来将JSON串转为对象，但此方法不可用来转带泛型的集合
     *
     * @param json     json
     * @param classOfT classOfT
     * @return T
     */
    fun <T> fromJson(json: String, classOfT: Class<T>): Any {
        return gson.fromJson(json, classOfT as Type)
    }

    /**
     * 用来将JSON串转为对象，此方法可用来转带泛型的集合，
     * 如：Type为new TypeToken<List></List><T>>(){}.getType()，
     * 其它类也可以用此方法调用，就是将List<T>替换为你想要转成的类
     *
     * @param json    json
     * @param typeOfT typeOfT
     * @return Object
    </T></T> */
    fun fromJson(json: String, typeOfT: Type): Any {
        return gson.fromJson(json, typeOfT)
    }
}