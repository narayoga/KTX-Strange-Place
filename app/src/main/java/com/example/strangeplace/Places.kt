package com.example.strangeplace

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Places(
    val name: String,
    val description: String,
    val photo: Int,
    val coordinate: String,
):Parcelable {

    fun getPhotoCustom(): Int {
        return photo
    }

    fun getNameCustom(): String {
        return name
    }

    fun getDescriptionCustom(): String {
        return description
    }
    fun getCoordinateCustom(): String {
        return coordinate
    }
}