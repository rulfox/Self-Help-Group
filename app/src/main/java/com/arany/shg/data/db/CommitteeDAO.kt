package com.arany.shg.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.arany.shg.data.models.Committee

@Dao
interface CommitteeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(committee: Committee)

    /*@Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)*/
}