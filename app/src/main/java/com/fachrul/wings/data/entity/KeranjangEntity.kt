package com.fachrul.wings.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keranjang")
data class KeranjangEntity(
    @PrimaryKey
    @field:ColumnInfo(name = "product_code")
    val productCode: String,
    @field:ColumnInfo(name = "quantity")
    var quantity: Int,
    @field:ColumnInfo(name = "user")
    var user: String
)