package com.arabam.android_assignment.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    @ColumnInfo(name = "uid")
    val uid: Int?,
    val nameSurname: String?,
    val phone: String?,
    val phoneFormatted: String?,
)
