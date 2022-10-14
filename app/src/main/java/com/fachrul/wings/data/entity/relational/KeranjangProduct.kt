package com.fachrul.wings.data.entity.relational

import androidx.room.Embedded
import androidx.room.Relation
import com.fachrul.wings.data.entity.KeranjangEntity
import com.fachrul.wings.data.entity.ProductEntity

data class KeranjangProduct (
    @Embedded
    val keranjangEntity: KeranjangEntity,

    @Relation(
        parentColumn = "product_code",
        entityColumn = "product_code"
    )
    val productEntity: ProductEntity
)