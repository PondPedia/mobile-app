package com.aetherized.view.pondpediaview.data.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aetherized.view.pondpediaview.data.local.entity.PondEntity
import com.aetherized.view.pondpediaview.data.local.entity.PondLogEntity
import com.aetherized.view.pondpediaview.data.local.entity.UpdatesEntity
import com.aetherized.view.pondpediaview.data.local.room.dao.PondDao
import com.aetherized.view.pondpediaview.data.local.room.dao.PondLogDao

@Database(entities = [PondEntity::class, PondLogEntity::class], version = 1, exportSchema = false)
abstract class PondDatabase : RoomDatabase() {
    abstract fun pondDao(): PondDao
    abstract fun pondLogDao(): PondLogDao

    companion object {
        @Volatile
        private var INSTANCE: PondDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): PondDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    PondDatabase::class.java,
                    "pondss.db"
                )
                .build()
            }
    }
}