package com.arabam.android_assignment.models

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("id")
    @ColumnInfo(name = "categoryId")
    val categoryId: Int,
    val name: String,
) : Serializable {

}