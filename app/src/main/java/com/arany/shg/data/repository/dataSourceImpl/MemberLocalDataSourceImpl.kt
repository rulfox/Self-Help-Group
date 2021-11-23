package com.arany.shg.data.repository.dataSourceImpl

import com.arany.shg.data.db.MemberDAO
import com.arany.shg.data.db.SelfHelpGroupDAO
import com.arany.shg.data.models.Member
import com.arany.shg.data.models.SelfHelpGroup
import com.arany.shg.data.repository.dataSource.MemberLocalDataSource
import com.arany.shg.data.repository.dataSource.SelfHelpGroupLocalDataSource
import kotlinx.coroutines.flow.Flow

class MemberLocalDataSourceImpl(private val memberDAO: MemberDAO) : MemberLocalDataSource {

    override suspend fun createMember(member: Member) {
        memberDAO.insert(member)
    }

    override fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>> = memberDAO.getMembersBySelfHelpGroupId(shgId)

    override suspend fun getMemberByMemberId(memberId: Int): Member = memberDAO.getMemberByMemberId(memberId)
}