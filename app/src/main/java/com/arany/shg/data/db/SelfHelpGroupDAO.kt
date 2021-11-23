package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.data.models.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface SelfHelpGroupDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selfHelpGroup: SelfHelpGroup)

    @Query("SELECT * FROM SelfHelpGroup")
    fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>>

    @Query("SELECT * FROM SelfHelpGroup WHERE shgId = :id")
    fun getSelfHelpGroupById(id: Int): SelfHelpGroup

    @Delete
    suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup)
}