package com.example.sampleapp.characterlist.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {
    @Insert
    fun insert(vararg character: Characters)

    @Query("select * from app_db order by id desc")
    fun getAll(): List<Characters>
}