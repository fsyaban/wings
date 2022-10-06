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

    private val resultProduct = MediatorLiveData<List<ProductEntity>>()

    fun getAllProduct():LiveData<List<ProductEntity>> {
        val localdata = productDao.getAllProduct()
        val localdata2 = productDao.getAllProduct()

        //init data jika kosong
        resultProduct.addSource(localdata){
            if (it.isEmpty()){
                appExecutors.diskIO.execute {
                    productDao.insertProduct(ProductEntity("SKUSKILNP","So Klin Pewangi",15000.0,"IDR",10.0,"13 cm x 10 cm","Pcs"))
                    productDao.insertProduct(ProductEntity("GIVBIRU","Giv Biru",11000.0,"IDR",0.0,"8 cm x 5 cm","Pcs"))
                    productDao.insertProduct(ProductEntity("SOKLINLIQ","So Klin Liquid",18000.0,"IDR",0.0,"25cm x 10 cm","Pcs"))
                    productDao.insertProduct(ProductEntity("GIVKUNING","Giv Kuning",10000.0,"IDR",0.0,"8cm x 5cm","Pcs"))

                }
            }
        }

        //assign value live data
        resultProduct.addSource(localdata2){
            resultProduct.value =it
        }

        //return liva data
        return resultProduct
    }


}