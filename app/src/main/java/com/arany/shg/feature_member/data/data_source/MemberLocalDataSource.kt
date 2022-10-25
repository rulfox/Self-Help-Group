package com.arany.shg.feature_member.data.data_source

import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

interface MemberLocalDataSource {
    suspend fun createMember(member: Member): Long
    fun getMembersOfSelfHelpGroup(shgId: Int): Flow<List<Member>>
    suspend fun getMemberByMemberId(memberId: Int): Member?
}