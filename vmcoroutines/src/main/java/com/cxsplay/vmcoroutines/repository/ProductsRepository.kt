package com.cxsplay.vmcoroutines.repository

import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.BaseList
import com.cxsplay.vmcoroutines.entity.Product
import com.cxsplay.vmcoroutines.net.RetrofitClient

/**
 * Created by CxS on 2019/6/20 15:44.
 * Description:
 */
class ProductsRepository {

    suspend fun getList(): Base<BaseList<Product>> {
        return RetrofitClient.getService().getProducts(1, 16)
    }
}
