package com.fachrul.wings.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fachrul.wings.data.entity.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product:ProductEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllProduct(products:List<ProductEntity>)

    @Query("SELECT * from product")
    fun getAllProduct(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE product_code =:productCode ")
    fun getProductByProductCode(productCode:String):LiveData<ProductEntity>
}