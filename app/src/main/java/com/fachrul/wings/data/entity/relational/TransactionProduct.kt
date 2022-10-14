package com.fachrul.wings.data.entity.relational

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.fachrul.wings.data.entity.KeranjangEntity
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.entity.TransactionDetailEntity

data class TransactionProduct (
    @Embedded
    val transactionDetailEntity: TransactionDetailEntity,

    @Relation(
        parentColumn = "product_code",
        entityColumn = "product_code"
    )
    val productEntity: ProductEntity
)