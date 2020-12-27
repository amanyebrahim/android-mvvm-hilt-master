package com.example.mvvmdagger.utils

import android.text.format.DateFormat
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


     fun getCurrentDate(): Date {
         return Date()

     }

     fun getDate(time: Long): String {
         val cal = Calendar.getInstance()
         cal.timeInMillis = time
         return DateFormat.format("HH:mm", cal).toString()
     }

    fun addMinutesTodate(dt:Date,minute:Int): String {

        var c = Calendar.getInstance ()
        try {
            c.setTime(dt)
        } catch (e:Exception) {
            e.printStackTrace()
        }
        c.add(Calendar.MINUTE, minute)
        // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
        var sdf1 = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        Log.e("minutes",sdf1.format (c.getTime()).toString())

        return sdf1.format (c.getTime()).toString()
    }





    fun toDate(date: String): Date {
       var dateFormat=Date()
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        try {
            dateFormat = format.parse(date)
            System.out.println(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
           return dateFormat
    }
 }