package com.example.mvvmdagger.ui.main

import android.annotation.SuppressLint
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.example.mvvmdagger.R
import com.example.mvvmdagger.data.model.Feed
import com.example.mvvmdagger.databinding.ActivityMainBinding
import com.example.mvvmdagger.ui.base.ParentActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class MainActivity : ParentActivity() {

    private val mViewModel: MainViewModel by viewModels()


    private lateinit var uiBinding: ActivityMainBinding


    var disposable: Disposable? = null


    override fun getLayoutResource(): View {
        uiBinding = ActivityMainBinding.inflate(layoutInflater)
        return uiBinding.root
    }

    override fun initializeComponents() {

        disposable = Observable.interval(1000, 2000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { fetchApiFeed() }


    }

    @SuppressLint("CheckResult")
    private fun fetchApiFeed() {
        mViewModel.getFeeds()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(this::handleResults, this::onError)


    }


    private fun onError(throwable: Throwable) {
//        Toast.makeText(this, throwable.message,
//                Toast.LENGTH_LONG).show()
        Log.e("error", throwable.message.toString())
    }


    private fun handleResults(feed: Feed?) {

        setProgressColor(feed)

        uiBinding.tvRsqr.text = feed?.RSRP.toString()

        uiBinding.tvRsrp.text = feed?.RSRQ.toString()

        uiBinding.tvSinr.text = feed?.SINR.toString()

        uiBinding.rsrpProgressBar.progress = feed?.RSRP ?: 0

        uiBinding.rsrqProgressBar.progress = feed?.RSRQ ?: 0

        uiBinding.snirProgressBar.progress = feed?.RSRP ?: 0


    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onResume() {
        super.onResume()
        if (disposable?.isDisposed == true) {
            disposable = Observable.interval(1000, 2000,
                    TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { fetchApiFeed() }
        }
    }
    /*
    * set progress color dependent on value
     */

    fun setProgressColor(feed: Feed?) {

        if (feed?.RSRP != null) {
            val drawable: Drawable = uiBinding.rsrpProgressBar.progressDrawable
            if (feed.RSRP <= -110) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }

            } else if (feed.RSRP > -110 && feed.RSRP < -100) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.red)) }


            } else if (feed.RSRP > -100 && feed.RSRP < -90) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.yellow)) }


            } else if (feed.RSRP > -90 && feed.RSRP < -80) {
                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.green)) }


            } else if (feed.RSRP > -80 && feed.RSRP < -70) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.darkGreen)) }


            } else if (feed.RSRP > -70 && feed.RSRP < -60) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightBlue)) }


            } else if (feed.RSRP > -60) {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.blue)) }


            } else {

                drawable.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }


            }

        }

        if (feed?.RSRQ != null) {

            val drawableRsrqPrpgress: Drawable = uiBinding.rsrqProgressBar.progressDrawable
            if (feed.RSRQ <= -19.5) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }

            } else if (feed.RSRQ > -19.5 && feed.RSRQ < -14) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightRed)) }


            } else if (feed.RSRQ > -14 && feed.RSRQ <-9) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightYellow)) }


            } else if (feed.RSRQ >-9 && feed.RSRQ <-3 ) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightGreen)) }


            } else if (feed.RSRQ > -3) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.zeti)) }


            } else {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }

            }
        }

        if (feed?.SINR != null) {
            val drawableSinrProgress: Drawable = uiBinding.snirProgressBar.progressDrawable

            if (feed.SINR <= 0) {
                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }

            } else if (feed.SINR in 1..4) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.redMedeium)) }


            } else if (feed.SINR in 6..9) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.orange)) }


            } else if (feed.SINR in 11..14) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.yellowMedieum)) }


            } else if (feed.SINR in 16..19) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.greenMedeium)) }


            } else if (feed.SINR in 21..24) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.zetiLight)) }


            } else if (feed.SINR in 26..29) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightBlue)) }


            }  else if (feed.SINR > 30) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.blue)) }


            } else {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }


            }


        }


    }


}