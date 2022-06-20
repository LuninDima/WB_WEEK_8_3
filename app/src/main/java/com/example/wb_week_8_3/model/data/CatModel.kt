package com.example.wb_week_8_3.model.data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
@Parcelize
data class CatModel (
    @SerialName("id")  val id:  String = "",
    @SerialName("url") val url: String = "",
): Parcelable