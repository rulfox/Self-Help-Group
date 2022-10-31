package com.arany.shg.presentation.di

import com.arany.shg.domain.repository.*
import com.arany.shg.domain.usecase.loanPayment.*
import com.arany.shg.domain.usecase.member.GetCommitteeUseCase
import com.arany.shg.domain.usecase.member.GetCommitteesOfSelfHelpGroupUseCase
import com.arany.shg.feature_attendance.domain.repository.AttendanceRepository
import com.arany.shg.feature_attendance.domain.usecase.*
import com.arany.shg.feature_committee.domain.repository.CommitteeRepository
import com.arany.shg.feature_committee.domain.usecase.*
import com.arany.shg.feature_fine.domain.repository.FineRepository
import com.arany.shg.feature_fine.domain.usecase.*
import com.arany.shg.feature_loan.domain.repository.LoanPaymentRepository
import com.arany.shg.feature_loan.domain.repository.LoanRepository
import com.arany.shg.feature_loan.domain.usecase.*
import com.arany.shg.feature_member.domain.repository.MemberRepository
import com.arany.shg.feature_member.domain.use_case.GetMemberUseCase
import com.arany.shg.feature_member.domain.use_case.GetMembersByShgIdUseCase
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository
import com.arany.shg.feature_onboarding.domain.use_case.LoginUseCases
import com.arany.shg.feature_onboarding.domain.use_case.VerifyLoginUseCase
import com.arany.shg.feature_role.domain.repository.RoleRepository
import com.arany.shg.feature_role.domain.usecase.*
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.domain.use_case.*
import com.arany.shg.feature_thrift.domain.repository.ThriftRepository
import com.arany.shg.feature_thrift.domain.usecase.*
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
    fun provideLoanUseCases(createLoanUseCase: CreateLoanUseCase, deleteLoanUseCase: DeleteLoanUseCase, getLoansFromCommitteeUseCase: GetLoansFromCommitteeUseCase, getLoansFromSelfHelpGroupUseCase: GetLoansFromSelfHelpGroupUseCase, getLoanUseCase: GetLoanUseCase, updateLoanUseCase: UpdateLoanUseCase): LoanUseCases {
        return LoanUseCases(createLoanUseCase, deleteLoanUseCase, getLoansFromCommitteeUseCase, getLoansFromSelfHelpGroupUseCase, getLoanUseCase, updateLoanUseCase)
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
    fun provideCreateAttendancesUseCase(attendanceRepository: AttendanceRepository): CreateAttendancesUseCase {
        return CreateAttendancesUseCase(attendanceRepository)
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

    @Singleton
    @Provides
    fun provideGetAttendancesUseCase(attendanceRepository: AttendanceRepository): GetAttendancesUseCase {
        return GetAttendancesUseCase(attendanceRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteAttendancesUseCase(attendanceRepository: AttendanceRepository): DeleteAttendanceUseCase {
        return DeleteAttendanceUseCase(attendanceRepository)
    }

    @Singleton
    @Provides
    fun provideAttendanceUseCases(createAttendanceUseCase: CreateAttendanceUseCase, createAttendancesUseCase: CreateAttendancesUseCase, deleteAttendanceUseCase: DeleteAttendanceUseCase, getAttendancesUseCase: GetAttendancesUseCase, getAttendanceUseCases: GetAttendanceUseCase, updateAttendanceUseCase: UpdateAttendanceUseCase): AttendanceUseCases {
        return AttendanceUseCases(createAttendancesUseCase = createAttendancesUseCase, createAttendanceUseCase = createAttendanceUseCase, deleteAttendanceUseCase = deleteAttendanceUseCase, getAttendancesUseCase = getAttendancesUseCase, getAttendanceUseCase = getAttendanceUseCases, updateAttendanceUseCase = updateAttendanceUseCase)
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

    @Singleton
    @Provides
    fun provideGetCommitteesUseCase(committeeRepository: CommitteeRepository): GetCommitteesUseCase {
        return GetCommitteesUseCase(committeeRepository)
    }

    @Singleton
    @Provides
    fun provideGetCommitteesWithDetailsUseCase(committeeRepository: CommitteeRepository): GetCommitteesWithDetailsUseCase {
        return GetCommitteesWithDetailsUseCase(committeeRepository)
    }

    @Singleton
    @Provides
    fun provideCommitteeUseCases(committeeRepository: CommitteeRepository): CommitteeUseCases {
        return CommitteeUseCases(
            createCommitteeUseCase = CreateCommitteeUseCase(committeeRepository),
            updateCommitteeUseCase = UpdateCommitteeUseCase(committeeRepository),
            deleteCommitteeUseCase = CreateCommitteeUseCase(committeeRepository),
            getCommitteeUseCase = com.arany.shg.feature_committee.domain.usecase.GetCommitteeUseCase(committeeRepository),
            getCommitteesUseCase = GetCommitteesUseCase(committeeRepository),
            getCommitteesWithDetailsUseCase = GetCommitteesWithDetailsUseCase(committeeRepository)
        )
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
    fun provideGetMembersByShgIdUseCase(memberRepository: MemberRepository): GetMembersByShgIdUseCase {
        return GetMembersByShgIdUseCase(memberRepository)
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
            getMemberUseCase = GetMemberUseCase(memberRepository),
            getMembersByShgIdUseCase = GetMembersByShgIdUseCase(memberRepository)
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

    /*Role*/
    @Singleton
    @Provides
    fun provideAddRoleUseCase(roleRepository: RoleRepository): AddRoleUseCase {
        return AddRoleUseCase(roleRepository)
    }

    @Singleton
    @Provides
    fun provideGetRoleByIdUseCase(roleRepository: RoleRepository): GetRoleByIdUseCase {
        return GetRoleByIdUseCase(roleRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteRoleUseCase(roleRepository: RoleRepository): DeleteRoleUseCase {
        return DeleteRoleUseCase(roleRepository)
    }

    @Singleton
    @Provides
    fun provideGetRolesUseCase(roleRepository: RoleRepository): GetRolesUseCase {
        return GetRolesUseCase(roleRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateRoleUseCase(roleRepository: RoleRepository): UpdateRoleUseCase {
        return UpdateRoleUseCase(roleRepository)
    }

    @Singleton
    @Provides
    fun provideRoleUseCases(addRoleUseCase: AddRoleUseCase, getRoleByIdUseCase: GetRoleByIdUseCase, deleteRoleUseCase: DeleteRoleUseCase, getRolesUseCase: GetRolesUseCase, updateRoleUseCase: UpdateRoleUseCase): RoleUseCases {
        return RoleUseCases(addRoleUseCase = addRoleUseCase, getRoleByIdUseCase = getRoleByIdUseCase, deleteRoleUseCase = deleteRoleUseCase, getRolesUseCase = getRolesUseCase, updateRoleUseCase = updateRoleUseCase)
    }

    /*Thrift*/
    @Singleton
    @Provides
    fun provideAddThriftUseCase(thriftRepository: ThriftRepository): AddThriftUseCase {
        return AddThriftUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideDeleteThriftUseCase(thriftRepository: ThriftRepository): DeleteThriftUseCase {
        return DeleteThriftUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideGetThriftsOfCommitteeUseCase(thriftRepository: ThriftRepository): GetThriftsOfCommitteeUseCase {
        return GetThriftsOfCommitteeUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideGetThriftsOfMemberUseCase(thriftRepository: ThriftRepository): GetThriftsOfMemberUseCase {
        return GetThriftsOfMemberUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideGetThriftUseCase(thriftRepository: ThriftRepository): GetThriftUseCase {
        return GetThriftUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideUpdateThriftUseCase(thriftRepository: ThriftRepository): UpdateThriftUseCase {
        return UpdateThriftUseCase(thriftRepository)
    }
    @Singleton
    @Provides
    fun provideThriftUseCases(addThriftUseCase: AddThriftUseCase,
                              deleteThriftUseCase: DeleteThriftUseCase,
                              getThriftsOfCommitteeUseCase: GetThriftsOfCommitteeUseCase,
                              getThriftsOfMemberUseCase: GetThriftsOfMemberUseCase,
                              getThriftUseCase: GetThriftUseCase,
                              updateThriftUseCase: UpdateThriftUseCase): ThriftUseCases {
        return ThriftUseCases(
            addThriftUseCase = addThriftUseCase,
            deleteThriftUseCase= deleteThriftUseCase,
            getThriftsOfCommitteeUseCase = getThriftsOfCommitteeUseCase,
            getThriftsOfMemberUseCase = getThriftsOfMemberUseCase,
            getThriftUseCase = getThriftUseCase,
            updateThriftUseCase = updateThriftUseCase
        )
    }
}