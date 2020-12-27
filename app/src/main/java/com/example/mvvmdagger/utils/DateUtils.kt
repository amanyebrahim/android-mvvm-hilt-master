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

    fun addMinutesToDate(dt:Date, minute:Int): String {
        val c = Calendar.getInstance ()
        try {
            c.time = dt
        } catch (e:Exception) {
            e.printStackTrace()
        }
        c.add(Calendar.MINUTE, minute)
        val sdf1 = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return sdf1.format (c.time).toString()
    }





    fun toDate(date: String): Date {
       var dateFormat=Date()
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        try {
            dateFormat = format.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
           return dateFormat
    }
 }