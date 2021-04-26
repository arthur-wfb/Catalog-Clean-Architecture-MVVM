package com.android.catalog.di.module

import dagger.Provides
import android.app.Application
import androidx.room.Room
import com.android.catalog.data.source.local.AppDatabase
import com.android.catalog.data.source.local.dao.ItemDao
import dagger.Module
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }


    @Provides
    internal fun provideItemDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.itemDao
    }
}