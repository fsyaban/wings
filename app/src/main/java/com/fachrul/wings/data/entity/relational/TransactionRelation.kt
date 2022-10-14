package com.fachrul.wings.data.entity.relational

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.TransactionHeaderEntity
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class TransactionRelation(
    @Embedded
    val transactionHeaderEntity: @RawValue  TransactionHeaderEntity,

    @Relation(
        parentColumn = "document_number",
        entityColumn = "document_number"
    )
    val transactionDetail: @RawValue List<TransactionDetailEntity>
):Parcelable