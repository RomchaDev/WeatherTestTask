package com.example.layer_presentation.main.home.list_items

import com.example.layer_domain.entity.list.ListItem
import com.example.layer_domain.entity.weather.Hour

data class HourListItem(
    val time: String,
    val hour: Hour
) : ListItem<HourListItem>