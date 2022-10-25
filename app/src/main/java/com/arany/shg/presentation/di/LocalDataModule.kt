package com.arany.shg.presentation.di

import com.arany.shg.data.db.*
import com.arany.shg.feature_attendance.data.datasource.AttendanceLocalDataSource
import com.arany.shg.feature_attendance.data.datasource.AttendanceLocalDataSourceImpl
import com.arany.shg.feature_committee.data.datasource.CommitteeLocalDataSource
import com.arany.shg.feature_committee.data.datasource.CommitteeLocalDataSourceImpl
import com.arany.shg.feature_member.data.data_source.MemberLocalDataSource
import com.arany.shg.feature_member.data.data_source.MemberLocalDataSourceImpl
import com.arany.shg.feature_onboarding.data.data_source.LoginDao
import com.arany.shg.feature_onboarding.data.data_source.LoginLocalDataSource
import com.arany.shg.feature_onboarding.data.data_source.LoginLocalDataSourceImpl
import com.arany.shg.feature_role.data.datasource.RoleLocalDataSource
import com.arany.shg.feature_role.data.datasource.RoleLocalDataSourceImpl
import com.arany.shg.feature_shg.data.data_source.SelfHelpGroupLocalDataSource
import com.arany.shg.feature_shg.data.data_source.SelfHelpGroupLocalDataSourceImpl
import com.arany.shg.feature_thrift.data.datasource.ThriftLocalDataSource
import com.arany.shg.feature_thrift.data.datasource.ThriftLocalDataSourceImpl
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

    @Singleton
    @Provides
    fun provideLoginLocalDataSource(loginDao: LoginDao): LoginLocalDataSource {
        return LoginLocalDataSourceImpl(loginDao)
    }

    @Singleton
    @Provides
    fun provideRoleLocalDataSource(roleDAO: RoleDAO): RoleLocalDataSource {
        return RoleLocalDataSourceImpl(roleDAO)
    }

    @Singleton
    @Provides
    fun provideCommitteeLocalDataSource(committeeDao: CommitteeDAO): CommitteeLocalDataSource {
        return CommitteeLocalDataSourceImpl(committeeDao)
    }

    @Singleton
    @Provides
    fun provideAttendanceLocalDataSource(attendanceDao: AttendanceDAO): AttendanceLocalDataSource {
        return AttendanceLocalDataSourceImpl(attendanceDao)
    }

    @Singleton
    @Provides
    fun provideThriftLocalDataSource(thriftDao: ThriftDao): ThriftLocalDataSource {
        return ThriftLocalDataSourceImpl(thriftDao)
    }
}













