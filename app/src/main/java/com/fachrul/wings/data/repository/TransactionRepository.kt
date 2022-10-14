package com.fachrul.wings.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.fachrul.wings.data.auth.AuthDbService
import com.fachrul.wings.data.dao.TransactionDao
import com.fachrul.wings.data.entity.KeranjangEntity
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.TransactionHeaderEntity
import com.fachrul.wings.data.entity.relational.KeranjangProduct
import com.fachrul.wings.data.helper.Const
import com.fachrul.wings.utils.AppExecutors
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class TransactionRepository(
    private val transactionDao: TransactionDao,
    private val appExecutors: AppExecutors,
    private val authDbService: AuthDbService
) {

    fun getAllKeranjang(): LiveData<List<KeranjangProduct>> = transactionDao.getAllKeranjang(authDbService.getUser())

    fun insertProductToKeranjang(productCode:String) {
        appExecutors.diskIO.execute {
            transactionDao.insertProductToKeranjang(KeranjangEntity(productCode,1,authDbService.getUser()))
        }
    }

    fun deleteKeranjang(keranjangProduct: KeranjangProduct) {
        appExecutors.diskIO.execute {
            transactionDao.deleteProductKeranjang(keranjangProduct.keranjangEntity.productCode)
        }
    }
    fun deleteAllKeranjang() {
        appExecutors.diskIO.execute {
            transactionDao.deleteAllKeranjang()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(keranjangList: List<KeranjangProduct>) {
        val documentCode = LocalDateTime.now().toString()
        val transactionDetails = arrayListOf<TransactionDetailEntity>()
        keranjangList.forEach {
            transactionDetails.add(
                TransactionDetailEntity(
                    documentCode + it.productEntity.productCode,
                    documentCode,
                    it.productEntity.productCode,
                    Const.getPrice(it.productEntity.price, it.productEntity.discount),
                    it.keranjangEntity.quantity,
                    it.productEntity.unit,
                    Const.getSubTotal(it),
                    it.productEntity.currency
                )
            )
        }
        appExecutors.diskIO.execute {
            transactionDao.insertTransactionHeader(
                TransactionHeaderEntity(
                    documentCode,
                    documentCode,
                    authDbService.getUser(),
                    Const.getTotal(keranjangList),
                    documentCode
                )
            )
            transactionDao.insertTransactionDetail(transactionDetails)
        }
    }

    fun getAllTransaction()= transactionDao.getAllTransaction(authDbService.getUser())

    fun getTransactionProduct(doc:String)= transactionDao.getTransactionDetailProduct(doc)
}