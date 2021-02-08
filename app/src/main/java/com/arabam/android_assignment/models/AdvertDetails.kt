package com.arabam.android_assignment.models

import android.os.Build
import android.text.Html
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class AdvertDetails(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var advertID: Int?,
    var title: String?,
    @Embedded
    var location: Location?,
    @Embedded
    var category: Category?,
    var modelName: String?,
    var price: Int?,
    var priceFormatted: String?,
    var date: String?,
    var dateFormatted: String?,
    @Embedded
    var photos: ArrayList<String>?,
    var properties: ArrayList<Property>?,
    var text: String?,
    @Embedded
    var userInfo: User?,
) : Serializable {


    @Suppress("DEPRECATION")
    fun getContent(): Any {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this.text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this.text)
        }
    }


    fun getKM(): String {
        return this.getProperty("km")
    }

    fun getYear(): String {
        return this.getProperty("year")
    }

    fun getGear(): String {
        return this.getProperty("gear")
    }

    fun getFuel(): String {
        return this.getProperty("fuel")
    }


    private fun getProperty(propertyName: String?): String {
        var value = "-"
        if (this.properties != null)
            for (item in this.properties!!) {
                if (item.name == propertyName)
                    value = item.value.toString()
            }

        return value
    }


}