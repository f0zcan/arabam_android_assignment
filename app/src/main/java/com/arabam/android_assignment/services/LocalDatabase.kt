package com.arabam.android_assignment.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.models.AdvertDetails
import com.arabam.android_assignment.models.MyConverter

@Database(entities = [Advert::class, AdvertDetails::class], version = 1)
@TypeConverters(MyConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var instance: LocalDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            LocalDatabase::class.java,
            "localDatabase"
        ).build()

    }

}
