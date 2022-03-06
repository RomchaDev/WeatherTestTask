package com.example.layer_presentation.main.home.list_items

import com.example.layer_domain.entity.list.ListItem
import com.example.layer_domain.entity.list.ListItemId
import com.example.layer_domain.entity.weather.Day

data class DayListItem(
    @ListItemId val dayStr: String,
    val city: String,
    val day: Day
) : ListItem<DayListItem>