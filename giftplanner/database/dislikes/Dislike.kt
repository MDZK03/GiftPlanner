package org.minhduc.giftplanner.database.dislikes

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.minhduc.giftplanner.database.person.Person

/**
 * Created by Minh Duc on 08/01/2025.
 * Copyright (c) 2024 Minh Duc. All rights reserved.
 */

@Parcelize
@Entity(
    tableName = "dislikes_table",
    foreignKeys = [
        (
            ForeignKey(
                entity = Person::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("uId"),
                onDelete = ForeignKey.CASCADE,
            )
        )
    ]
)
data class Dislike(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "uId") val uId: Long,
    @ColumnInfo(name = "colors") val colors: List<String>,
    @ColumnInfo(name = "activities") val activities: List<String>,
    @ColumnInfo(name = "allergies") val allergies: List<String>,
    @ColumnInfo(name = "films") val films: List<String>,
    @ColumnInfo(name = "books") val books: List<String>,
) : Parcelable