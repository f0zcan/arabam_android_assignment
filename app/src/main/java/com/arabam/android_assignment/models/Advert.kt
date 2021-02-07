package com.arabam.android_assignment.models

import android.os.Build
import android.text.Html
import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Advert(
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
    var photo: String?,
    @Embedded
    var photos: ArrayList<String>?,
    @Embedded
    var properties: ArrayList<Property>?,
    var text: String?,
    @Embedded
    var userInfo: User?,


    ) : Serializable {
    var listOrder: Int? = 0

    fun photoUrl(): String? {
        return this.photo?.replace("{0}", "800x600").toString()
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

    fun getContent(): Any {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this.text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(this.text)
        }
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

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false // null check

        if (javaClass != other.javaClass)
            return false // type check


        val mOther = other as Advert
        return advertID == mOther.advertID
    }

    override fun hashCode(): Int {
        return Objects.hash(advertID)
    }

}


