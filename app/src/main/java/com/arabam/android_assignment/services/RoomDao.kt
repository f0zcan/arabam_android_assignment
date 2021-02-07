package com.arabam.android_assignment.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.arabam.android_assignment.models.Advert

@Dao
interface RoomDao {

    @Insert
    suspend fun insertAll(vararg advertList: Advert): List<Long>

    @Query("SELECT * FROM Advert ORDER BY listOrder ASC")
    suspend fun getAdvertList(): List<Advert>

    @Query("SELECT * FROM Advert WHERE id = :id")
    suspend fun getAdvertDetails(id: Int?): Advert

    @Query("DELETE FROM Advert")
    suspend fun deleteAdvertList()
}