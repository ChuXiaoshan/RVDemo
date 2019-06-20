package com.cxsplay.vmcoroutines.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cxsplay.vmcoroutines.base.BaseViewModel
import com.cxsplay.vmcoroutines.entity.Product
import com.cxsplay.vmcoroutines.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by CxS on 2019/6/20 14:32.
 * Description:
 */
class ProductsModel : BaseViewModel() {

    private val repository by lazy { ProductsRepository() }

    val data: MutableLiveData<List<Product>> = MutableLiveData()

    fun getList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getList()
            }
            data.value = result.d?.data
        }
    }
}