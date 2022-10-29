package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_committee.data.model.CommitteeWithDetails
import com.arany.shg.feature_member.data.model.Member

@Dao
interface CommitteeDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addCommittee(committee: Committee): Long

    @Query("SELECT * FROM Committee WHERE committeeId =:committeeId")
    suspend fun getCommittee(committeeId: Int): Committee?

    @Query("SELECT * FROM Committee WHERE shgId = :shgId")
    suspend fun getCommitteesBySelfHelpGroupId(shgId: Int): List<Committee>

    @Query("SELECT * FROM Committee WHERE shgId = :shgId")
    suspend fun getCommitteesOfSelfHelpGroupWithDetails(shgId: Int): List<CommitteeWithDetails>

    @Update
    suspend fun updateCommittee(committee: Committee)

    @Delete
    suspend fun deleteMember(member: Member)
}