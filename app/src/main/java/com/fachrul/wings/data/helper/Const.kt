package com.fachrul.wings.data.helper

import com.fachrul.wings.data.entity.relational.KeranjangProduct

object Const {
    fun getPrice(price: Double, discount: Double) = price - (price * discount / 100)
    fun getSubTotal(keranjangProduct: KeranjangProduct) =
        getPrice(
            keranjangProduct.productEntity.price,
            keranjangProduct.productEntity.discount
        ) * keranjangProduct.keranjangEntity.quantity

    fun getTotal(subTotalList: List<KeranjangProduct>): Double {
        var total = 0.0
        subTotalList.forEach {
            total += getSubTotal(it)
        }
        return total
    }
}