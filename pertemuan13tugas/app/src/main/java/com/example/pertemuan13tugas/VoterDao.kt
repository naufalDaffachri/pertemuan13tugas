package com.example.pertemuan13tugas

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VoterDao {
    @Insert
    suspend fun insertVoter(voter: Voter)

    @Update
    suspend fun updateVoter(voter: Voter)

    @Delete
    suspend fun deleteVoter(voter: Voter)

    @Query("SELECT * FROM voters")
    suspend fun getAllVoters(): List<Voter>
}