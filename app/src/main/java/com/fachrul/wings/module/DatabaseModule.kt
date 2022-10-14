package com.fachrul.wings.module

import android.content.Context
import com.fachrul.wings.data.PenjualanDatabase
import com.fachrul.wings.data.auth.AuthDbService
import com.fachrul.wings.data.auth.AuthDbServicePreference
import com.fachrul.wings.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) = PenjualanDatabase.getDatabase(context,
        CoroutineScope(SupervisorJob())
    )

    @Provides
    @Singleton
    fun providesLoginDao(penjualanDatabase: PenjualanDatabase) = penjualanDatabase.loginDao()

    @Provides
    @Singleton
    fun providesProductDao(penjualanDatabase: PenjualanDatabase) = penjualanDatabase.productDao()

    @Provides
    @Singleton
    fun providesTransactionDao(penjualanDatabase: PenjualanDatabase) = penjualanDatabase.transactionDao()

    @Provides
    @Singleton
    fun providesExecutor() = AppExecutors()

    @Provides
    @Singleton
    fun providesAuthDb(@ApplicationContext context: Context):AuthDbService = AuthDbServicePreference(context)

}