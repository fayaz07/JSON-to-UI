package com.mohammadfayaz.network.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UIDataModel(
    @SerializedName("hint")
    val hint: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("uitype")
    val uiType: String,
    @SerializedName("value")
    val value: String
): Serializable