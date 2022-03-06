package com.example.layer_presentation.main.home

import com.example.layer_data.time.TimeWorker
import com.example.layer_domain.entity.AppStateEntity
import com.example.layer_domain.use_cases.GetWeatherUseCase
import com.example.layer_presentation.core.main.AppState
import com.example.layer_presentation.core.main.BaseViewModel
import com.example.layer_presentation.core.navigation.AppNavigator
import com.example.layer_presentation.main.home.list_items.DayListItem
import com.example.layer_presentation.main.home.list_items.HourListItem

class HomeViewModel(
    override val navigator: AppNavigator,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val timeWorker: TimeWorker
) : BaseViewModel<HomeStateEntity>() {

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Loading())

            val weatherEntity = getWeatherUseCase.execute(DEFAULT_LOCATION)
            val days = weatherEntity.daily.map { day ->
                DayListItem(
                    dayStr = timeWorker.getDay(day.getDate()),
                    city = DEFAULT_LOCATION,
                    day = day
                )
            }.toMutableList()

            val hours = weatherEntity.hourly.map { hour ->
                HourListItem(
                    time = timeWorker.getTime(hour.getDate()),
                    hour = hour
                )
            }

            val current = days[0]

            val homeStateEntity = HomeStateEntity(current, days, hours)

            mSharedFlow.emit(AppState.Success(homeStateEntity))
        }
    }

    companion object {
        private const val DEFAULT_LOCATION = "Запорожье"
    }
}