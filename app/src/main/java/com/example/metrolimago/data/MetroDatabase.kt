package com.example.metrolimago.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.metrolimago.model.Estacion

@Database(entities = [Estacion::class], version = 1)
abstract class MetroDatabase : RoomDatabase() {
    abstract fun estacionDao(): EstacionDao

    companion object {
        @Volatile
        private var INSTANCE: MetroDatabase? = null

        fun getDatabase(context: Context): MetroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MetroDatabase::class.java,
                    "metro_lima_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
