package com.example.submission1intermediate.vstate

data class Resource<T>(val status: State, val data: T?, val message: String?) {

    companion object{
        fun <T> success(data:T?):Resource<T> = Resource(State.SUCCESS, data, null)
        fun <T> error(msg: String?, data:T?):Resource<T> = Resource(State.ERROR, data, msg)
        fun <T> loading(data:T?):Resource<T> = Resource(State.LOADING, data, null)
    }
}