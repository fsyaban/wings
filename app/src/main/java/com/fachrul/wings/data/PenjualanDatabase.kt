package com.fachrul.wings.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fachrul.wings.data.dao.LoginDao
import com.fachrul.wings.data.dao.ProductDao
import com.fachrul.wings.data.dao.TransactionDao
import com.fachrul.wings.data.entity.*
import com.fachrul.wings.data.helper.InitialDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [LoginEntity::class,
        ProductEntity::class,
        TransactionDetailEntity::class,
        TransactionHeaderEntity::class, KeranjangEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PenjualanDatabase : RoomDatabase() {
    abstract fun loginDao(): LoginDao
    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao


    companion object {
        @Volatile
        private var instance: PenjualanDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): PenjualanDatabase {
            if (instance == null) {
                synchronized(PenjualanDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PenjualanDatabase::class.java, "penjualan_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                instance?.let { database ->
                                    applicationScope.launch {
                                        val productDao = database.productDao()
                                        productDao.insertAllProduct(InitialDataSource.getProduct())
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return instance as PenjualanDatabase
        }
    }
}