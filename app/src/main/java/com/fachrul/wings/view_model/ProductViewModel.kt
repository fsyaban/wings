package com.fachrul.wings.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    application: Application,
    private val productRepository: ProductRepository
) : AndroidViewModel(application) {
    var productLiveData :LiveData<List<ProductEntity>>?=null

    init {
        productLiveData = productRepository.getAllProduct()
    }

}