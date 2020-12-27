package com.example.mvvmdagger.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import androidx.activity.viewModels
import com.example.mvvmdagger.R
import com.example.mvvmdagger.data.model.Feed
import com.example.mvvmdagger.databinding.ActivityMainBinding
import com.example.mvvmdagger.ui.base.ParentActivity
import com.example.mvvmdagger.utils.DateUtils
import com.example.mvvmdagger.utils.LogUtils
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs


@AndroidEntryPoint
class MainActivity : ParentActivity() {

    private val mViewModel: MainViewModel by viewModels()


    private lateinit var uiBinding: ActivityMainBinding


    var disposable: CompositeDisposable = CompositeDisposable()

    var valuesRsrp = ArrayList<Entry>()
    var valuesRsqr = ArrayList<Entry>()
    var valuesSinr = ArrayList<Entry>()

    var rsrpFeed = ArrayList<Float>()
    var rsqrFeed = ArrayList<Float>()
    var sinrFeed = ArrayList<Float>()
    var xInitial = .9f


    override fun getLayoutResource(): View {
        uiBinding = ActivityMainBinding.inflate(layoutInflater)
        return uiBinding.root
    }

    override fun initializeComponents() {

        disposable.add(Observable.interval(1000, 2000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { fetchApiFeed() }
        )

        initializeChart()


    }

    @SuppressLint("CheckResult")
    private fun fetchApiFeed() {
        mViewModel.getFeeds()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe(this::handleResults, this::onError)


    }


    private fun onError(throwable: Throwable) {
        mToaster?.makeToast(getString(R.string.error_connection))
        LogUtils.debug( throwable.message.toString())
    }


    private fun handleResults(feed: Feed?) {

        setProgressColor(feed)

        uiBinding.tvRsqr.text = feed?.RSRQ.toString()

        uiBinding.tvRsrp.text = feed?.RSRP.toString()

        uiBinding.tvSinr.text = feed?.SINR.toString()

        uiBinding.rsrpProgressBar.progress = feed?.RSRP?.let { abs(it) } ?: 0

        uiBinding.rsrqProgressBar.progress = feed?.RSRQ?.let { abs(it) } ?: 0

        uiBinding.snirProgressBar.progress = feed?.SINR?.let { abs(it) } ?: 0

        feed?.RSRP?.toFloat()?.let { rsrpFeed.add(it) }

        feed?.RSRQ?.toFloat()?.let { rsqrFeed.add(it) }

        feed?.SINR?.toFloat()?.let { sinrFeed.add(it) }


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    /*
    * set progress color dependent on value
     */

    private fun setProgressColor(feed: Feed?) {

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


            } else if (feed.RSRQ > -14 && feed.RSRQ < -9) {

                drawableRsrqPrpgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.lightYellow)) }


            } else if (feed.RSRQ > -9 && feed.RSRQ < -3) {

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


            } else if (feed.SINR > 30) {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.blue)) }


            } else {

                drawableSinrProgress.colorFilter = mContext?.let { LightingColorFilter(-0x1000000, it.resources.getColor(R.color.black)) }


            }


        }


    }


    private fun initializeChart() {


        uiBinding.chart.description.isEnabled = false
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        uiBinding.chart.setMaxVisibleValueCount(60)

        // scaling can now only be done on x- and y-axis separately
        uiBinding.chart.setPinchZoom(false)

        uiBinding.chart.setDrawGridBackground(false)

        val xAxis = uiBinding.chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        uiBinding.chart.axisLeft.setDrawGridLines(false)

        xAxis.granularity = .5f


        val l: Legend = uiBinding.chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val rightAxis = uiBinding.chart.axisRight
        rightAxis.isEnabled = false



        xAxis.valueFormatter = object : ValueFormatter() {

            override fun getFormattedValue(value: Float): String {
                when (value) {
                    1f -> {

                        return DateUtils.getDate(System.currentTimeMillis())
                    }
                    1.5f -> {
                        return DateUtils.addMinutesToDate(Date(), 1)
                    }
                    2f -> {
                        return DateUtils.addMinutesToDate(Date(), 2)
                    }
                    2.5f -> {
                        return DateUtils.addMinutesToDate(Date(), 3)
                    }
                    3f -> {
                        return DateUtils.addMinutesToDate(Date(), 4)
                    }
                    else -> return "0"
                }

            }
        }


        disposable.add(Observable.interval(5,
                TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                    if (xInitial <= 3f&&rsrpFeed.size>0)
                        setChartData()

                })
    }


    private fun setChartData() {

        xInitial = (xInitial + .1).toFloat()

        valuesRsrp.add(Entry(xInitial, rsrpFeed[rsrpFeed.size - 1]))

        valuesRsqr.add(Entry(xInitial, rsqrFeed[rsqrFeed.size - 1]))

        valuesSinr.add(Entry(xInitial, sinrFeed[sinrFeed.size - 1]))




        val set1 : LineDataSet
        val set2: LineDataSet
        val set3: LineDataSet

        if (uiBinding.chart.getData() != null && uiBinding.chart.getData().getDataSetCount() > 0) {
            set1 = uiBinding.chart.getData().getDataSetByIndex(0) as LineDataSet
            set2 = uiBinding.chart.getData().getDataSetByIndex(1) as LineDataSet
            set3 = uiBinding.chart.getData().getDataSetByIndex(2) as LineDataSet
            set1.values = valuesRsrp
            set2.values = valuesRsqr
            set3.values = valuesSinr
            uiBinding.chart.data.notifyDataChanged()
            uiBinding.chart.notifyDataSetChanged()
            uiBinding.chart.visibility = View.INVISIBLE
            uiBinding.chart.visibility = View.VISIBLE

        } else {
            // create a dataset and give it a type

            // create a dataset and give it a type
            set1 = LineDataSet(valuesRsrp, "RSRP")

            set1.axisDependency = AxisDependency.LEFT
            set1.color = ColorTemplate.getHoloBlue()
            set1.setCircleColor(Color.BLUE)
            set1.lineWidth = 2f
            set1.circleRadius = 3f
            set1.fillAlpha = 65
            set1.fillColor = ColorTemplate.getHoloBlue()
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.setDrawCircleHole(false)


            // create a dataset and give it a type
            set2 = LineDataSet(valuesRsqr, "RSRQ")
            set2.axisDependency = AxisDependency.RIGHT
            set2.color = Color.RED
            set2.setCircleColor(Color.RED)
            set2.lineWidth = 2f
            set2.circleRadius = 3f
            set2.fillAlpha = 65
            set2.fillColor = Color.RED
            set2.setDrawCircleHole(false)
            set2.highLightColor = Color.rgb(244, 117, 117)

            set3 = LineDataSet(valuesSinr, "SINR")
            set3.axisDependency = AxisDependency.RIGHT
            set3.color = Color.YELLOW
            set3.setCircleColor(Color.YELLOW)
            set3.lineWidth = 2f
            set3.circleRadius = 3f
            set3.fillAlpha = 65
            set3.fillColor = ColorTemplate.colorWithAlpha(Color.YELLOW, 200)
            set3.setDrawCircleHole(false)
            set3.highLightColor = Color.rgb(244, 117, 117)

            // create a data object with the data sets
            val data = LineData(set1, set2, set3)

            data.setDrawValues(false)


            // set data
            uiBinding.chart.notifyDataSetChanged()
            uiBinding.chart.data = data
            uiBinding.chart.data.notifyDataChanged()
            uiBinding.chart.visibility = View.VISIBLE


        }
    }


}