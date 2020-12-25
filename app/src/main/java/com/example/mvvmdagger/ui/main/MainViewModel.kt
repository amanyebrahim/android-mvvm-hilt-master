package com.example.mvvmdagger.ui.main

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdagger.data.model.Feed

import com.example.mvvmdagger.repositery.Repositery
import com.example.mvvmdagger.utils.NetworkState
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.Observable

@ActivityScoped
class MainViewModel @ViewModelInject constructor
                                    (@ApplicationContext context: Context,
                                     private val repository: Repositery) : ViewModel() {


    /*
     * Getter method for the network state
     */
    fun getNetworkState(): LiveData<NetworkState?>? {
        return repository.getNetworkState()
    }


    fun getFeeds(): Observable<Feed?>? {
        return repository.getFeeds()
    }


}