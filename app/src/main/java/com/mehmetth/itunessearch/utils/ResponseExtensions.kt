package com.mehmetth.itunessearch

import retrofit2.Response

fun <T> Response<T>.getRemoteResponse() : Resource<T> {
    return if(isSuccessful){
        this.body()?.let { body->
            Resource.Success(body)
        } ?: run {
            Resource.Error( "API Error")
        }
    }
    else{
        Resource.Error("Server Error : ${code()}")
    }
}