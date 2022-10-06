package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.fachrul.wings.data.entity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(application: Application) :AndroidViewModel(application) {
    val listProductChart = arrayListOf<ProductEntity>()
}