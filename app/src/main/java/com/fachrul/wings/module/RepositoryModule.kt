package com.fachrul.wings.module

import com.fachrul.wings.data.dao.LoginDao
import com.fachrul.wings.data.dao.ProductDao
import com.fachrul.wings.data.repository.LoginRepository
import com.fachrul.wings.data.repository.ProductRepository
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
        executors: AppExecutors
    ) = LoginRepository(loginDao, executors)


    @Provides
    fun providesProductRepository(
        productDao: ProductDao,
        executors: AppExecutors
    ) = ProductRepository(productDao, executors)
}