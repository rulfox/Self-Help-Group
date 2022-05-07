package com.arany.shg.feature_member.data.data_source

import com.arany.shg.data.db.MemberDAO
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

class MemberLocalDataSourceImpl(private val memberDAO: MemberDAO) : MemberLocalDataSource {

    override suspend fun createMember(member: Member) {
        memberDAO.insert(member)
    }

    override fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>> = memberDAO.getMembersBySelfHelpGroupId(shgId)

    override suspend fun getMemberByMemberId(memberId: Int): Member = memberDAO.getMemberByMemberId(memberId)
}