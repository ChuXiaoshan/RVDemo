package com.cxsplay.appkotlin.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cxsplay.appkotlin.BR

/**
 * Created by CxS on 2019/5/22 14:20.
 * Description:
 */

class Product : BaseObservable() {

    @get:Bindable
    var name: String? = null
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    var pic: String? = null
        set(pic) {
            field = pic
            notifyPropertyChanged(BR.pic)
        }
    @get:Bindable
    var price: String? = null
        set(price) {
            field = price
            notifyPropertyChanged(BR.price)
        }
    @get:Bindable
    var sale: Int = 0
        set(sale) {
            field = sale
            notifyPropertyChanged(BR.sale)
        }
    @get:Bindable
    var subTitle: String? = null
        set(subTitle) {
            field = subTitle
            notifyPropertyChanged(BR.subTitle)
        }
    @get:Bindable
    var description: String? = null
        set(description) {
            field = description
            notifyPropertyChanged(BR.description)
        }

    override fun toString(): String {
        return "Product1{" +
                "name='" + this.name + '\''.toString() +
                ", pic='" + this.pic + '\''.toString() +
                ", price='" + this.price + '\''.toString() +
                ", sale=" + this.sale +
                ", subTitle='" + this.subTitle + '\''.toString() +
                ", description='" + this.description + '\''.toString() +
                '}'.toString()
    }
}