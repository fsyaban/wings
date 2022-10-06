package com.fachrul.wings.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fachrul.wings.data.dao.LoginDao
import com.fachrul.wings.data.dao.ProductDao
import com.fachrul.wings.data.entity.LoginEntity
import com.fachrul.wings.data.entity.ProductEntity
import com.fachrul.wings.data.entity.TransactionDetailEntity
import com.fachrul.wings.data.entity.TransactionHeaderEntity

@Database(
    entities = [LoginEntity::class,
        ProductEntity::class,
        TransactionDetailEntity::class,
        TransactionHeaderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PenjualanDatabase : RoomDatabase() {
    abstract fun loginDao():LoginDao
    abstract fun productDao(): ProductDao


    companion object {
        @Volatile
        private var instance: PenjualanDatabase? = null
        fun getInstance(context: Context): PenjualanDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PenjualanDatabase::class.java, "Penjualan.db"
                ).build()
            }
    }
}