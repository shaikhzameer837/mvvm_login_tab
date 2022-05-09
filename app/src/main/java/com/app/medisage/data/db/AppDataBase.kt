package com.app.medisage.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.medisage.data.db.entity.User



@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = "database.db"
        @Volatile private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDataBase::class.java,
            DB_NAME
        ).build()
    }
}