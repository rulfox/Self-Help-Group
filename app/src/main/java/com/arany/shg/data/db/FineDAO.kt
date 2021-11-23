package com.arany.shg.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface FineDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert()

    /*@Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)*/
}