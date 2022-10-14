package com.fachrul.wings.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.fachrul.wings.data.dao.ProductDao
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.utils.AppExecutors

class ProductRepository(
    private val productDao: ProductDao,
    private val appExecutors: AppExecutors
) {
    fun getAllProduct():LiveData<List<ProductEntity>> = productDao.getAllProduct()
}