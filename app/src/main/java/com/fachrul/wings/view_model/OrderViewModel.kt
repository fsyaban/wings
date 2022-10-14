package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fachrul.wings.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    application: Application,
    transactionRepository: TransactionRepository
) : AndroidViewModel(application) {
    val transactionLiveData =  transactionRepository.getAllTransaction()
}