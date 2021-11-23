package com.arany.shg.presentation.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arany.shg.data.db.AppDatabase
import com.arany.shg.data.db.MemberDAO
import com.arany.shg.data.db.SelfHelpGroupDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): AppDatabase {
        val dbBuilder = Room.databaseBuilder(app, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
        dbBuilder.setQueryCallback({ sqlQuery, bindArgs ->
            println("SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor())
        return dbBuilder.build()
    }

    @Singleton
    @Provides
    fun provideSelfHelpGroupDAO(appDatabase: AppDatabase): SelfHelpGroupDAO {
        return appDatabase.getSelfHelpGroupDAO()
    }

    @Singleton
    @Provides
    fun provideMemberDao(appDatabase: AppDatabase): MemberDAO {
        return appDatabase.getMemberDAO()
    }
}