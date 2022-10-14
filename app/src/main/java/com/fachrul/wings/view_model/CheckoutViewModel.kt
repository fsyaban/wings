package com.fachrul.wings.view_model

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fachrul.wings.data.entity.relational.KeranjangProduct
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.helper.Const
import com.fachrul.wings.data.helper.Const.getTotal
import com.fachrul.wings.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    application: Application,
    private val transactionRepository: TransactionRepository
) : AndroidViewModel(application) {
    var checkoutData:LiveData<List<KeranjangProduct>> = transactionRepository.getAllKeranjang()
    val subTotalMap= hashMapOf<String,KeranjangProduct>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertTransaction(){
        viewModelScope.launch {
            transactionRepository.insertTransaction(subTotalMap.values.toList())
        }
    }


    fun deleteAllKeranjang(){
        viewModelScope.launch {
            transactionRepository.deleteAllKeranjang()
        }
    }

    fun isQuantityEmpty(): Boolean {
        subTotalMap.values.forEach {
            if (it.keranjangEntity.quantity == 0) return true
        }
        return false
    }


}