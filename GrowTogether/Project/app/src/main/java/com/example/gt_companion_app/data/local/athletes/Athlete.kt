package com.example.gt_companion_app.data.local.athletes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "athletes")
class Athlete(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val athleteId: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "sur_name")
    val surName: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,

)