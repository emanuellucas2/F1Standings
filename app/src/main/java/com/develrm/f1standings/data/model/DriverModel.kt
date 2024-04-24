package com.develrm.f1standings.data.model

import java.io.Serializable

 data class DriverModel (
    val position: String = "",
    val name: String = "",
    val points: String = "",
    val constructorName: String = "",
    val urlImage: String = ""
) : Serializable