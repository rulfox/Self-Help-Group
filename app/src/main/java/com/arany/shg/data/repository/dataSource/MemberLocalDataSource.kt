package com.arany.shg.data.repository.dataSource

import com.arany.shg.data.models.Member
import com.arany.shg.data.models.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

interface MemberLocalDataSource {
    suspend fun createMember(member: Member)
    fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>>
    suspend fun getMemberByMemberId(memberId: Int): Member?
}