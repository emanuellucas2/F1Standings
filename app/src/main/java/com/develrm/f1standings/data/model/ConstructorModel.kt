package com.develrm.f1standings.data.model

import java.io.Serializable

data class ConstructorModel(
    val name: String = "",
    val points: String = "",
    val position: String = "",
    val urlImage: String = ""
) : Serializable