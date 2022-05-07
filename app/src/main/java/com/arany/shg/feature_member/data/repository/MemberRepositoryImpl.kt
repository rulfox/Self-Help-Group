package com.arany.shg.feature_member.data.repository

import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.data.data_source.MemberLocalDataSource
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_member.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(private val memberLocalDataSource: MemberLocalDataSource) :
    MemberRepository {

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