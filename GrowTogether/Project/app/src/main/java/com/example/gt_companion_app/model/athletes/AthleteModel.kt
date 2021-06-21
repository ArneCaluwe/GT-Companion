package com.example.gt_companion_app.model.athletes

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.example.gt_companion_app.data.local.athletes.Athlete
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AthleteModel (
    val id: String,@ColumnInfo(name = "id")
    val firstName: String,
    val surName: String,
    val email: String,
    val imageUrl: String?,
) : Parcelable{
    fun toDataBaseModel(): Athlete {
        return Athlete(id, String.format("%s %s", firstName, surName), firstName, surName, email, imageUrl)
    }
}