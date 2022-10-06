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

    @Query("SELECT * from product")
    fun getAllProduct(): LiveData<List<ProductEntity>>

}