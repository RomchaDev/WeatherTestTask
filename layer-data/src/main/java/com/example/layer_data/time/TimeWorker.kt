package com.example.layer_data.time

import java.util.*

interface TimeWorker {
    fun getTime(date: Date): String
    fun getDay(date: Date): String
    fun getWeekDay(date: Date): String
    fun currentDate(): Date
    fun dateFromMillis(millis: Long): Date
}