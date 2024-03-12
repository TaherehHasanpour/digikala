package com.example.digikala.di

import com.example.digikala.data.remote.CheckoutApiInterface
import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {
    @Provides
    @Singleton
    fun provideProfileApiInterface(retrofit: Retrofit): CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)
}