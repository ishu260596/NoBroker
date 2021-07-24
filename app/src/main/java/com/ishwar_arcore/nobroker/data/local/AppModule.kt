package com.ishwar_arcore.nobroker.data.local

import android.app.Application
import androidx.room.Room
import com.ishwar_arcore.nobroker.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule, provides application wide singletons
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDb(app: Application): ItemDatabase {
        return Room.databaseBuilder(app, ItemDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRunDao(db: ItemDatabase): ItemDAO {
        return db.getItemDAO()
    }
}