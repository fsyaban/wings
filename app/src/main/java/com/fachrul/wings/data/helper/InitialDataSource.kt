package com.fachrul.wings.data.helper

import com.fachrul.wings.data.entity.ProductEntity

object InitialDataSource {
    fun getProduct(): List<ProductEntity> = listOf(
        ProductEntity("SKUSKILNP", "So Klin Pewangi", 15000.0, "IDR", 10.0, "13 cm x 10 cm", "Pcs"),
        ProductEntity("GIVBIRU", "Giv Biru", 11000.0, "IDR", 0.0, "8 cm x 5 cm", "Pcs"),
        ProductEntity("SOKLINLIQ", "So Klin Liquid", 18000.0, "IDR", 0.0, "25cm x 10 cm", "Pcs"),
        ProductEntity("GIVKUNING", "Giv Kuning", 10000.0, "IDR", 0.0, "8cm x 5cm", "Pcs")
    )
}