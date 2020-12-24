package com.example.mvvmdagger.repositery


import androidx.lifecycle.LiveData
import com.example.mvvmdagger.data.network.ApiInterface
import com.example.mvvmdagger.utils.NetworkState


import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repositery @Inject constructor(val apiService: ApiInterface?){



    private var networkState: LiveData<NetworkState?>? =null

//    /*
//       * Step 1: We are initializing an Executor class
//       * Step 2: We are getting an instance of the DataSourceFactory class
//       * Step 3: We are initializing the network state liveData as well.
//       *         This will update the UI on the network changes that take place
//       *         For instance, when the data is getting fetched, we would need
//       *         to display a loader and when data fetching is completed, we
//       *         should hide the loader.
//       * Step 4: We need to configure the PagedList.Config.
//       * Step 5: We are initializing the pageList using the config we created
//       *         in Step 4 and the DatasourceFactory we created from Step 2
//       *         and the executor we initialized from Step 1.
//       */
//    fun getArticleLiveData(): LiveData<PagedList<Article?>?> {
//        val feedDataFactory = FeedDatafactory(apiService)
//        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData()!!
//       ) { dataSource -> dataSource.getNetworkState() }
//        val pagedListConfig = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setInitialLoadSizeHint(10)
//            .setPageSize(20).build()
//        val articleLiveData = LivePagedListBuilder(feedDataFactory, pagedListConfig)
//            //  .setFetchExecutor(executor)
//            .build()
//        return articleLiveData
//    }

    fun getNetworkState(): LiveData<NetworkState?>? {
        return networkState
    }


}