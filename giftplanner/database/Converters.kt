package org.minhduc.giftplanner.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

class Converters {
    @TypeConverter
    fun fromListToString(listHabit: List<String>): String = Gson().toJson(listHabit)

    @TypeConverter
    fun fromStringToList(string: String): List<String> {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson<ArrayList<String>?>(string, listType)
    }

}