package com.example.layer_presentation.main.home.list_items

import com.example.layer_domain.entity.weather.Day

data class DayListItem(
    val dayStr: String,
    val day: Day
)