package com.example.gt_companion_app.model.bouts

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.gt_companion_app.data.local.bouts.Bout
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.model.sets.SetModel
import kotlinx.android.parcel.Parcelize
import java.time.LocalDate

@Parcelize
data class BoutModel (
    val id: String,
    val activeSet: Int,
    val name: String,
    val date: Long,
    val sets : List<SetModel>
): Parcelable{
    fun toDatabaseModel(): Bout {
        return Bout(id, activeSet, name, date)
    }
}