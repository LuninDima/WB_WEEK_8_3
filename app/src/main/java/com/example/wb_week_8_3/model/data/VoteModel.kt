package com.example.wb_week_8_3.model.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoteModel(
    @SerialName("image_id")   val image_Id: String,
    @SerialName("sub_id") val sub_Id: String = "",
)