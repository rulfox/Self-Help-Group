package com.arany.shg.presentation.di

import com.arany.shg.domain.repository.*
import com.arany.shg.domain.usecase.attendance.*
import com.arany.shg.domain.usecase.committee.CreateCommitteeUseCase
import com.arany.shg.domain.usecase.fine.*
import com.arany.shg.domain.usecase.loan.*
import com.arany.shg.domain.usecase.loanPayment.*
import com.arany.shg.domain.usecase.member.GetCommitteeUseCase
import com.arany.shg.domain.usecase.member.GetCommitteesOfSelfHelpGroupUseCase
import com.arany.shg.feature_member.domain.use_case.GetMemberUseCase
import com.arany.shg.feature_member.domain.repository.MemberRepository
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository
import com.arany.shg.feature_onboarding.domain.use_case.LoginUseCases
import com.arany.shg.feature_onboarding.domain.use_case.VerifyLoginUseCase
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    /*Fine*/
    @Singleton
    @Provides
    fun provideCreateFineUseCase(fineRepository: FineRepository): CreateFineUseCase {
        return CreateFineUseCase(fineRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteFineUseCase(fineRepository: FineRepository): DeleteFineUseCase {
        return DeleteFineUseCase(fineRepository)
    }

    @Singleton
    @Provides
    fun provideGetFinesOfCommitteeUseCase(fineRepository: FineRepository): GetFinesOfCommitteeUseCase {
        return GetFinesOfCommitteeUseCase(fineRepository)
    }

    @Singleton
    @Provides
    fun provideGetFineUseCase(fineRepository: FineRepository): GetFineUseCase {
        return GetFineUseCase(fineRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateFineUseCase(fineRepository: FineRepository): UpdateFineUseCase {
        return UpdateFineUseCase(fineRepository)
    }

    /*Loan*/
    @Singleton
    @Provides
    fun provideCreateLoanUseCase(loanRepository: LoanRepository): CreateLoanUseCase {
        return CreateLoanUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteLoanUseCase(loanRepository: LoanRepository): DeleteLoanUseCase {
        return DeleteLoanUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoansFromCommitteeUseCase(loanRepository: LoanRepository): GetLoansFromCommitteeUseCase {
        return GetLoansFromCommitteeUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoansFromSelfHelpGroupUseCase(loanRepository: LoanRepository): GetLoansFromSelfHelpGroupUseCase {
        return GetLoansFromSelfHelpGroupUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoanUseCase(loanRepository: LoanRepository): GetLoanUseCase {
        return GetLoanUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateLoanUseCase(loanRepository: LoanRepository): UpdateLoanUseCase {
        return UpdateLoanUseCase(loanRepository)
    }

    @Singleton
    @Provides
    fun provideCreateLoanPaymentUseCase(loanPaymentRepository: LoanPaymentRepository): CreateLoanPaymentUseCase {
        return CreateLoanPaymentUseCase(loanPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteLoanPaymentUseCase(loanPaymentRepository: LoanPaymentRepository): DeleteLoanPaymentUseCase {
        return DeleteLoanPaymentUseCase(loanPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoanPaymentsFromCommitteeUseCase(loanPaymentRepository: LoanPaymentRepository): GetLoanPaymentsFromCommitteeUseCase {
        return GetLoanPaymentsFromCommitteeUseCase(loanPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoanPaymentsFromSelfHelpGroupUseCase(loanPaymentRepository: LoanPaymentRepository): GetLoanPaymentsFromSelfHelpGroupUseCase {
        return GetLoanPaymentsFromSelfHelpGroupUseCase(loanPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideGetLoanPaymentUseCase(loanPaymentRepository: LoanPaymentRepository): GetLoanPaymentUseCase {
        return GetLoanPaymentUseCase(loanPaymentRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateLoanPaymentUseCase(loanPaymentRepository: LoanPaymentRepository): UpdateLoanPaymentUseCase {
        return UpdateLoanPaymentUseCase(loanPaymentRepository)
    }

    /*Attendance*/
    @Singleton
    @Provides
    fun provideCreateAttendanceUseCase(attendanceRepository: AttendanceRepository): CreateAttendanceUseCase {
        return CreateAttendanceUseCase(attendanceRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateAttendanceUseCase(attendanceRepository: AttendanceRepository): UpdateAttendanceUseCase {
        return UpdateAttendanceUseCase(attendanceRepository)
    }

    @Singleton
    @Provides
    fun provideGetAttendanceUseCase(attendanceRepository: AttendanceRepository): GetAttendanceUseCase {
        return GetAttendanceUseCase(attendanceRepository)
    }

    /*Committee*/
    @Singleton
    @Provides
    fun provideCreateCommitteeUseCase(committeeRepository: CommitteeRepository): CreateCommitteeUseCase {
        return CreateCommitteeUseCase(committeeRepository)
    }

    @Singleton
    @Provides
    fun provideGetCommitteesOfSelfHelpGroupUseCase(committeeRepository: CommitteeRepository): GetCommitteesOfSelfHelpGroupUseCase {
        return GetCommitteesOfSelfHelpGroupUseCase(committeeRepository)
    }

    @Singleton
    @Provides
    fun provideGetCommitteeUseCase(committeeRepository: CommitteeRepository): GetCommitteeUseCase {
        return GetCommitteeUseCase(committeeRepository)
    }

    /*SelfHelpGroup*/
    @Singleton
    @Provides
    fun provideGetSelfHelpGroupByIdUseCase(selfHelpGroupRepository: SelfHelpGroupRepository): GetSelfHelpGroupByIdUseCase {
        return GetSelfHelpGroupByIdUseCase(selfHelpGroupRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllSelfHelpGroupsUseCase(selfHelpGroupRepository: SelfHelpGroupRepository): GetAllSelfHelpGroupsUseCase {
        return GetAllSelfHelpGroupsUseCase(selfHelpGroupRepository)
    }

    @Singleton
    @Provides
    fun provideCreateSelfHelpGroupUseCase(selfHelpGroupRepository: SelfHelpGroupRepository): CreateSelfHelpGroupUseCase {
        return CreateSelfHelpGroupUseCase(selfHelpGroupRepository)
    }

    /*Member*/
    @Singleton
    @Provides
    fun provideCreateMemberUseCase(memberRepository: MemberRepository): CreateMemberUseCase {
        return CreateMemberUseCase(memberRepository)
    }

    @Singleton
    @Provides
    fun provideGetMembersOfSelfHelpGroupUseCase(memberRepository: MemberRepository): GetMembersOfSelfHelpGroupUseCase {
        return GetMembersOfSelfHelpGroupUseCase(memberRepository)
    }

    @Singleton
    @Provides
    fun provideGetMemberUseCase(memberRepository: MemberRepository): GetMemberUseCase {
        return GetMemberUseCase(memberRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCases(loginRepository: LoginRepository): LoginUseCases {
        return LoginUseCases(
            verifyLoginUseCase = VerifyLoginUseCase(loginRepository)
        )
    }

    @Provides
    @Singleton
    fun provideMemberUseCases(memberRepository: MemberRepository): MemberUseCases {
        return MemberUseCases(
            createMemberUseCase = com.arany.shg.feature_member.domain.use_case.CreateMemberUseCase(memberRepository),
            getMemberUseCase = GetMemberUseCase(memberRepository)
        )
    }

    @Provides
    @Singleton
    fun provideSelfHelpGroupUseCases(selfHelpGroupRepository: SelfHelpGroupRepository, memberRepository: MemberRepository): SelfHelpGroupUseCases {
        return SelfHelpGroupUseCases(
            createSelfHelpGroupUseCase = CreateSelfHelpGroupUseCase(selfHelpGroupRepository),
            getAllSelfHelpGroupsUseCase = GetAllSelfHelpGroupsUseCase(selfHelpGroupRepository),
            getMembersOfSelfHelpGroupUseCase = GetMembersOfSelfHelpGroupUseCase(memberRepository),
            getSelfHelpGroupByIdUseCase = GetSelfHelpGroupByIdUseCase(selfHelpGroupRepository)
        )
    }
}