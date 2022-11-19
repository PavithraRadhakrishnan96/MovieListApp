package com.example.sampleapp.characterlist.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Characters::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getCharacterDAO(): CharacterDao

}
