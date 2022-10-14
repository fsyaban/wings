package com.fachrul.wings.module

import com.fachrul.wings.data.auth.AuthDbService
import com.fachrul.wings.data.dao.LoginDao
import com.fachrul.wings.data.dao.ProductDao
import com.fachrul.wings.data.dao.TransactionDao
import com.fachrul.wings.data.repository.LoginRepository
import com.fachrul.wings.data.repository.ProductRepository
import com.fachrul.wings.data.repository.TransactionRepository
import com.fachrul.wings.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun providesLoginRepository(
        loginDao: LoginDao,
        executors: AppExecutors,
        authDbService: AuthDbService
    ) = LoginRepository(loginDao, executors,authDbService)


    @Provides
    fun providesProductRepository(
        productDao: ProductDao,
        executors: AppExecutors
    ) = ProductRepository(productDao, executors)

    @Provides
    fun providesTransactionRepository(
        transactionDao: TransactionDao,
        executors: AppExecutors,
        authDbService: AuthDbService
    ) = TransactionRepository(transactionDao, executors,authDbService)
}