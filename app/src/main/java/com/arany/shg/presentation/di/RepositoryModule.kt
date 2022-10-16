package com.arany.shg.presentation.di

import com.arany.shg.feature_member.data.repository.MemberRepositoryImpl
import com.arany.shg.feature_shg.data.repository.SelfHelpGroupRepositoryImpl
import com.arany.shg.feature_member.data.data_source.MemberLocalDataSource
import com.arany.shg.feature_shg.data.data_source.SelfHelpGroupLocalDataSource
import com.arany.shg.feature_member.domain.repository.MemberRepository
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_onboarding.data.data_source.LoginLocalDataSource
import com.arany.shg.feature_onboarding.data.repository.LoginRepositoryImpl
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository
import com.arany.shg.feature_role.data.datasource.RoleLocalDataSource
import com.arany.shg.feature_role.data.repository.RoleRepositoryImpl
import com.arany.shg.feature_role.domain.repository.RoleRepository
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

    @Provides
    @Singleton
    fun provideLoginRepository(loginLocalDataSource: LoginLocalDataSource): LoginRepository {
        return LoginRepositoryImpl(loginLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideRoleRepository(roleLocalDataSource: RoleLocalDataSource): RoleRepository {
        return RoleRepositoryImpl(roleLocalDataSource)
    }
}