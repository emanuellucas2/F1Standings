package com.develrm.f1standings.data.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConstructorModel(
    val constructor: String = "",
    val points: String = "",
    val position: String = "",
    val urlImage: String = ""
) : Serializable