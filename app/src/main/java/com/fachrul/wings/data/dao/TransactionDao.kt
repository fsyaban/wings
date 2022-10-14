package com.fachrul.wings.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fachrul.wings.data.entity.KeranjangEntity
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.TransactionHeaderEntity
import com.fachrul.wings.data.entity.relational.KeranjangProduct
import com.fachrul.wings.data.entity.relational.TransactionProduct
import com.fachrul.wings.data.entity.relational.TransactionRelation

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductToKeranjang(keranjangEntity: KeranjangEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionHeader(transactionHeaderEntity: TransactionHeaderEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTransactionDetail(transactionDetails:List<TransactionDetailEntity>)

    @Query("SELECT * FROM transaction_header WHERE user=:user")
    fun getAllTransaction(user:String):LiveData<List<TransactionRelation>>

    @Transaction
    @Query("SELECT * from keranjang WHERE user=:user")
    fun getAllKeranjang(user:String): LiveData<List<KeranjangProduct>>

    @Query("DELETE FROM keranjang WHERE product_code = :productCode")
    fun deleteProductKeranjang(productCode:String)

    @Query("DELETE FROM keranjang")
    fun deleteAllKeranjang()

    @Transaction
    @Query("SELECT * from transaction_detail WHERE document_code=:document")
    fun getTransactionDetailProduct(document:String): LiveData<TransactionProduct>
}