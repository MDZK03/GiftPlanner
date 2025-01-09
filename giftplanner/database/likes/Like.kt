package org.minhduc.giftplanner.database.likes

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
    tableName = "likes_table",
    foreignKeys = [(
            ForeignKey(
                entity = Person::class,
                parentColumns = arrayOf("person_name"),
                childColumns = arrayOf("person"),
                onDelete = ForeignKey.CASCADE
            )
        )
    ]
)
data class Like(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "person") val person: String,
    @ColumnInfo(name = "colors") val colors: List<String>,
    @ColumnInfo(name = "hobbies") val hobbies: List<String>,
    @ColumnInfo(name = "food") val food: List<String>,
    @ColumnInfo(name = "films") val films: List<String>,
) : Parcelable