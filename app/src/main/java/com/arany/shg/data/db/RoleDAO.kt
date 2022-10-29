package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.data.models.Role

@Dao
interface RoleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRole(role: Role): Long

    @Update
    suspend fun updateRole(role: Role)

    @Query("SELECT * FROM role")
    suspend fun getRoles(): List<Role>

    @Query("SELECT * FROM role WHERE roleId = :roleId")
    fun getRoleById(roleId: Int): Role?

    @Delete
    suspend fun deleteRole(role: Role)
}