package com.develrm.f1standings.ui.state

sealed class ResourseState<T> (
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T): ResourseState<T>(data)
    class Error<T>(message: String, data: T? = null): ResourseState<T>(data, message)
    class Loading<T> : ResourseState<T>()
    class Empty<T>: ResourseState<T>()
}