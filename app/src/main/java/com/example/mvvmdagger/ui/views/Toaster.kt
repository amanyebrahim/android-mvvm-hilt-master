package com.example.mvvmdagger.ui.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.mvvmdagger.R


class Toaster(var mContext: Context?) : Toast(mContext) {


    fun makeToast(message: String?) {
        val inflater = mContext?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.toast_custom_layout, null)
        val tvToast = view.findViewById<View>(R.id.tv_toast_message) as TextView
        val toast = Toast(mContext)
        tvToast.text = message
        toast.view = view
        toast.duration = LENGTH_LONG
        toast.show()
    }



}