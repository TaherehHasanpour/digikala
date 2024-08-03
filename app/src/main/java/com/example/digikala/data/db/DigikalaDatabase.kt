package com.example.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.profile.FevItem

@Database(entities = [CartItem::class ,FevItem::class], version = 2, exportSchema = false)
abstract class DigikalaDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao
}