package com.example.layer_presentation.main.home.list_items

import com.example.layer_domain.entity.list.ListItem
import com.example.layer_domain.entity.list.ListItemId
import com.example.layer_domain.entity.weather.Day

class CurrentListItem(
    val city: String,
    @ListItemId val date: String,
    val day: Day
) : ListItem<CurrentListItem>