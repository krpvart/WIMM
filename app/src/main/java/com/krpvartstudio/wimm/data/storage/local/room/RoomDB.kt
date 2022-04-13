package com.krpvartstudio.wimm.data.storage.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [AccountEntity::class, TransactionEntity::class], exportSchema = false, version = 1)
abstract class RoomDB: RoomDatabase() {
    abstract fun getDao(): RoomDao
}
