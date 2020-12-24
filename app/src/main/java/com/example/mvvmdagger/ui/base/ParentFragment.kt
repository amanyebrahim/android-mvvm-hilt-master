package com.example.mvvmdagger.ui.base


import android.content.Context
import android.os.Bundle

import android.view.View

import androidx.fragment.app.Fragment
import com.example.mvvmdagger.ui.views.Toaster



abstract class ParentFragment : Fragment() {



    var mSavedInstanceState: Bundle? = null
    var mToaster: Toaster? = null
    var mContext: Context? = null




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mContext = activity

        this.mSavedInstanceState = savedInstanceState

        mToaster = Toaster(mContext)

          initializeComponents(view)


    }



    protected abstract fun initializeComponents(view: View?)






}