package com.arany.shg.presentation.di

import com.arany.shg.data.db.MemberDAO
import com.arany.shg.data.db.SelfHelpGroupDAO
import com.arany.shg.data.repository.dataSource.MemberLocalDataSource
import com.arany.shg.data.repository.dataSource.SelfHelpGroupLocalDataSource
import com.arany.shg.data.repository.dataSourceImpl.MemberLocalDataSourceImpl
import com.arany.shg.data.repository.dataSourceImpl.SelfHelpGroupLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideMemberLocalDataSource(memberDAO: MemberDAO): MemberLocalDataSource {
        return MemberLocalDataSourceImpl(memberDAO)
    }

    @Singleton
    @Provides
    fun provideSelfHelpGroupLocalDataSource(selfHelpGroupDAO: SelfHelpGroupDAO): SelfHelpGroupLocalDataSource {
        return SelfHelpGroupLocalDataSourceImpl(selfHelpGroupDAO)
    }
}













