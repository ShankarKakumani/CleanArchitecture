package com.example.cleanarchitecture.utils

open class Resource<out T> constructor(
    val status: ResourceState,
    val data: T?,
    val message: String?,
    val exception: Throwable? = null
) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String): Resource<T> {
        return Resource(ResourceState.ERROR, null, message)
    }

    fun <T> loading(): Resource<T> {
        return Resource(ResourceState.LOADING, null, null)
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}