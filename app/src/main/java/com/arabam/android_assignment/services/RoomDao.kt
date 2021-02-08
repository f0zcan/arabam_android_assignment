package com.arabam.android_assignment.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.models.AdvertDetails

@Dao
interface RoomDao {

    @Insert
    suspend fun insertAll(vararg advertList: Advert): List<Long>

    @Query("SELECT * FROM Advert ORDER BY uuid ASC")
    suspend fun getAdvertList(): List<Advert>

    @Query("SELECT * FROM AdvertDetails WHERE id = :id")
    suspend fun getAdvertDetails(id: Int): AdvertDetails

    @Query("DELETE FROM Advert")
    suspend fun deleteAdvertList()
}