package com.example.mvvmdagger.ui.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity

import com.example.mvvmdagger.ui.views.Toaster


abstract class ParentActivity: AppCompatActivity() {

    protected var mActivity: AppCompatActivity? = null

    protected var mContext: Context? = null
    protected var mToaster: Toaster? = null
    protected var mSavedInstanceState: Bundle? = null




    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mActivity = this

        mToaster = Toaster(mContext)



        // set layout resources
        setContentView(getLayoutResource())
        mSavedInstanceState = savedInstanceState


        initializeComponents()




    }


    protected abstract fun initializeComponents()



    /**
     * @return the layout resource id
     */
    protected abstract fun getLayoutResource(): View







}