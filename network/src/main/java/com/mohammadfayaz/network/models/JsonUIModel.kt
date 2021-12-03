package com.mohammadfayaz.network.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonUIModel(
    @SerializedName("heading-text")
    val headingText: String,
    @SerializedName("logo-url")
    val logoUrl: String,
    @SerializedName("uidata")
    val uiData: List<UIDataModel>
): Serializable