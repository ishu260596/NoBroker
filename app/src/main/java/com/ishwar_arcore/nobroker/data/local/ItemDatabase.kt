package com.ishwar_arcore.nobroker.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ishwar_arcore.nobroker.utils.DATABASE_NAME

@Database(
    entities = [ItemEntity::class],
    version = 1
)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun getItemDAO(): ItemDAO

    companion object {

        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {

            if (INSTANCE == null) {

                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    DATABASE_NAME
                )
                builder.fallbackToDestructiveMigration()
                INSTANCE = builder.build()
                return INSTANCE!!

            } else {
                return INSTANCE!!
            }
        }
    }

}