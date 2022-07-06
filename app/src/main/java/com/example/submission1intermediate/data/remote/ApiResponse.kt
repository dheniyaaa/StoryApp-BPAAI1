package com.example.submission1intermediate.data.remote

class ApiResponse <T> (val status: StatusResponse, val body: T, val messsage: String?) {

    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)
        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg)
    }
}