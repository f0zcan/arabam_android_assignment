package com.arabam.android_assignment.models

import java.io.Serializable

data class Location(var cityName: String?, var townName: String?) : Serializable {


    override fun toString(): String {
        return "${this.cityName} / ${this.townName}"
    }

}