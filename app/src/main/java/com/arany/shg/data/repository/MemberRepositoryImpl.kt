package com.arany.shg.data.repository

import com.arany.shg.data.models.Member
import com.arany.shg.data.repository.dataSource.MemberLocalDataSource
import com.arany.shg.data.util.Resource
import com.arany.shg.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(private val memberLocalDataSource: MemberLocalDataSource) : MemberRepository {

    override suspend fun createMember(member: Member) {
        memberLocalDataSource.createMember(member)
    }

    override suspend fun getMember(memberId: Int): Resource<Member> {
        return memberLocalDataSource.getMemberByMemberId(memberId)?.let {
            Resource.Success(it)
        } ?: Resource.Error("Unable to find Member with id $memberId")
    }

    override suspend fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>> {
        return memberLocalDataSource.getMembersOfSelfHelpGroup(shgId)
    }
}