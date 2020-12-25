package com.example.mvvmdagger.repositery


import androidx.lifecycle.LiveData
import com.example.mvvmdagger.data.model.Feed
import com.example.mvvmdagger.data.network.ApiInterface
import com.example.mvvmdagger.utils.NetworkState
import io.reactivex.Observable


import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repositery @Inject constructor(val apiService: ApiInterface?){



    private var networkState: LiveData<NetworkState?>? =null



    fun getNetworkState(): LiveData<NetworkState?>? {
        return networkState
    }


    fun getFeeds(): Observable<Feed?>? {
        return apiService?.fetchFeed()
    }

}