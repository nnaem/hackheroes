package com.hackheroes.newoldtown.model.service.module

import com.hackheroes.newoldtown.model.service.AccountService
import com.hackheroes.newoldtown.model.service.StorageService
import com.hackheroes.newoldtown.model.service.impl.AccountServiceImpl
import com.hackheroes.newoldtown.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}