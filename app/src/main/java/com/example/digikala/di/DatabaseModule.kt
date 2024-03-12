package com.example.digikala.di

import android.content.Context
import androidx.room.Room
import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, DigikalaDatabase::class.java, DATABASE_NAME).build()
}