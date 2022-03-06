package com.example.layer_presentation.main.home

import com.example.layer_domain.entity.AppStateEntity
import com.example.layer_presentation.main.home.list_items.DayListItem
import com.example.layer_presentation.main.home.list_items.HourListItem

data class HomeStateEntity(
    val current: DayListItem?,
    val days: List<DayListItem>?,
    val hours: List<HourListItem>?
) : AppStateEntity