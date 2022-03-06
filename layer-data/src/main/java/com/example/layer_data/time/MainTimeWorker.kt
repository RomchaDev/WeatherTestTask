package com.example.layer_data.time

import java.text.SimpleDateFormat
import java.util.*

class MainTimeWorker : TimeWorker {
    override fun getTime(date: Date): String {
        val sdf = SimpleDateFormat(TIME_PATTERN, Locale.getDefault())
        return sdf.format(date)
    }

    override fun getDay(date: Date): String {
        val weekDay = getWeekDay(date)
        val sdf = SimpleDateFormat(NO_WEEK_PATTERN, Locale.getDefault())
        val noWeekDate = sdf.format(date)
        return "$weekDay, $noWeekDate"
    }

    override fun getWeekDay(date: Date): String {
        val sdf = SimpleDateFormat(WEEK_DAY_PATTERN, Locale.getDefault())
        return sdf.format(date).uppercase(Locale.getDefault())
    }

    override fun currentDate() = Date()

    override fun dateFromMillis(millis: Long) = Date(millis)

    companion object {
        private const val WEEK_DAY_PATTERN = "EE"
        private const val TIME_PATTERN = "HH:mm"
        private const val NO_WEEK_PATTERN = "d MMMM"
    }
}
