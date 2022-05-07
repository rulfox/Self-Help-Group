package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import kotlinx.coroutines.flow.Flow

@Dao
interface SelfHelpGroupDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selfHelpGroup: SelfHelpGroup): Long?

    @Query("SELECT * FROM SelfHelpGroup")
    fun getAllSelfHelpGroups(): Flow<List<SelfHelpGroup>>

    @Query("SELECT * FROM SelfHelpGroup WHERE shgId = :id")
    fun getSelfHelpGroupById(id: Int): SelfHelpGroup

    @Delete
    suspend fun deleteSelfHelpGroup(selfHelpGroup: SelfHelpGroup)
}