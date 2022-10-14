package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fachrul.wings.data.entity.KeranjangEntity
import com.fachrul.wings.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    application: Application,
    private val transactionRepository: TransactionRepository
) : AndroidViewModel(application) {

    fun insertProduct(productCode:String){
        transactionRepository.insertProductToKeranjang(productCode)
    }

}