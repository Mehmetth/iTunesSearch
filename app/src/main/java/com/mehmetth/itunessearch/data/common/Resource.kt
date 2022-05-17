package com.mehmetth.itunessearch

sealed class Resource <out T> {
    data class Success <out T>(val successData: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
    data class Loading(val loading: Boolean = true): Resource<Nothing>()
}