package com.example.digikala.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.digikala.data.model.basket.CartItem
import com.example.digikala.data.model.profile.FevItem
import com.example.digikala.util.Constants.FAVORITE_LIST_TABLE

@Database(entities = [CartItem::class ,FevItem::class], version = 2, exportSchema = false)
abstract class DigikalaDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        val MIGRATION_1_2 :Migration =object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXIST `${FAVORITE_LIST_TABLE}` " +
                            "(`id` TEXT NOT NULL, " +
                            "`discountPercent` INTEGER NOT NULL, " +
                            "`image` TEXT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`price` INTEGER NOT NULL, " +
                            "`seller` TEXT NOT NULL, " +
                            "`star` REAL NOT NULL, " +
                            "PRIMARY KEY(`id`))"

                )
            }

        }
    }
}