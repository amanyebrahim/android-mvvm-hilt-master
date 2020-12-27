package com.example.mvvmdagger.utils

import android.util.Log

object LogUtils {
    
    private const val TAG = "MvvmHilltTag"


    fun debug(text: String?) {
        Log.v(TAG, text!!)
    }
}