package org.minhduc.giftplanner.database.person

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Parcelize
@Entity(tableName = "people_table", indices = [Index(value = ["person_name"], unique = true)])
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "person_name") val name: String = "",
    @ColumnInfo(name = "dob") val birthday: String = "",
    @ColumnInfo(name = "relationship") val relationship: String = ""
) : Parcelable