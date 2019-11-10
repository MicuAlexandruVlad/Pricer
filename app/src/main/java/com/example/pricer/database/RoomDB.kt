package com.example.pricer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pricer.dao.ReviewDAO
import com.example.pricer.models.Review

@Database(entities = [Review::class], version = 1, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {

    abstract fun reviewDao(): ReviewDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "test1"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}