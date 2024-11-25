package com.example.pertemuan13tugas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Voter::class], version = 1)
abstract class VoterDatabase : RoomDatabase() {
    abstract fun voterDao(): VoterDao
}
