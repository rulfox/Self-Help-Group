package com.arany.shg.presentation.di

import android.app.Application
import androidx.room.Room
import com.arany.shg.data.db.*
import com.arany.shg.feature_onboarding.data.data_source.LoginDao
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

    @Singleton
    @Provides
    fun provideLoginDao(appDatabase: AppDatabase): LoginDao {
        return appDatabase.getLoginDao()
    }

    @Singleton
    @Provides
    fun provideRoleDao(appDatabase: AppDatabase): RoleDAO {
        return appDatabase.getRoleDao()
    }

    @Singleton
    @Provides
    fun provideCommitteeDao(appDatabase: AppDatabase): CommitteeDAO {
        return appDatabase.getCommitteeDao()
    }

    @Singleton
    @Provides
    fun provideAttendanceDao(appDatabase: AppDatabase): AttendanceDAO {
        return appDatabase.getAttendanceDao()
    }
}