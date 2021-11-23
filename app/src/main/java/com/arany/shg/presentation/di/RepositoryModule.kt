package com.arany.shg.presentation.di

import com.arany.shg.data.repository.MemberRepositoryImpl
import com.arany.shg.data.repository.SelfHelpGroupRepositoryImpl
import com.arany.shg.data.repository.dataSource.MemberLocalDataSource
import com.arany.shg.data.repository.dataSource.SelfHelpGroupLocalDataSource
import com.arany.shg.domain.repository.MemberRepository
import com.arany.shg.domain.repository.SelfHelpGroupRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMemberRepository(memberLocalDataSource: MemberLocalDataSource): MemberRepository {
        return MemberRepositoryImpl(memberLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideSelfHelpGroupRepository(selfHelpGroupLocalDataSource: SelfHelpGroupLocalDataSource): SelfHelpGroupRepository {
        return SelfHelpGroupRepositoryImpl(selfHelpGroupLocalDataSource)
    }
}