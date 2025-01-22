package org.minhduc.giftplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.minhduc.giftplanner.database.dislikes.Dislike
import org.minhduc.giftplanner.database.dislikes.DislikeDao
import org.minhduc.giftplanner.database.likes.Like
import org.minhduc.giftplanner.database.likes.LikeDao
import org.minhduc.giftplanner.database.person.Person
import org.minhduc.giftplanner.database.person.PersonDao

/**
 * Created by Minh Duc on 06/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Database(
    entities = [(Person::class), (Like::class), (Dislike::class)],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun likeDao(): LikeDao
    abstract fun dislikeDao(): DislikeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gift_planner_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}