package com.example.mvvmdagger.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp



@HiltAndroidApp
class AppController: MultiDexApplication() {


    val TAG = AppController::class.java.simpleName


    companion object {
        var mInstance: AppController? = null

        @Synchronized
        fun getInstance(): AppController? {
            return mInstance


        }
    }
    override fun onCreate() {
        super.onCreate()
        mInstance = this

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}