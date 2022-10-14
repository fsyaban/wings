package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.fachrul.wings.data.entity.relational.TransactionProduct
import com.fachrul.wings.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    application: Application,
    private val transactionRepository: TransactionRepository
) : AndroidViewModel(application) {
    fun getTransactionProductLiveData(doc:String) = transactionRepository.getTransactionProduct(doc)
}