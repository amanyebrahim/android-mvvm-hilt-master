package com.example.mvvmdagger.utils

class NetworkState(private var status: Status?, private  var msg: String?) {


    enum class Status {
        RUNNING, SUCCESS, FAILED
    }


    companion object
    {
        var LOADED =  NetworkState (Status.SUCCESS, "Success")
        var LOADING =  NetworkState (Status.RUNNING, "Running")
    }

    fun getStatus(): Status? {
        return status
    }

    fun getMsg(): String? {
        return msg
    }


}