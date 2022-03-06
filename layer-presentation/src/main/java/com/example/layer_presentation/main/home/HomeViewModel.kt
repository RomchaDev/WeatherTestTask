package com.example.layer_presentation.main.home

import com.example.layer_data.time.TimeWorker
import com.example.layer_domain.entity.weather.WeatherEntity
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

    private var current: DayListItem? = null

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Loading())

            val weatherEntity = getWeatherUseCase.execute(DEFAULT_LOCATION)

            val days = prepareDays(weatherEntity)
            val hours = prepareHours(weatherEntity)
            val current = current ?: run {
                days[0].isSelected = true
                makeFirstCurrent(days)
            }

            val homeStateEntity = HomeStateEntity(current, days, hours)

            mSharedFlow.emit(AppState.Success(homeStateEntity))
        }
    }

    private fun makeFirstCurrent(days: MutableList<DayListItem>) = getUpdatedCurrent(days[0])

    private fun prepareHours(weatherEntity: WeatherEntity) =
        weatherEntity.hourly.map { hour ->
            HourListItem(
                time = timeWorker.getTime(hour.getDate()),
                hour = hour
            )
        }

    private fun prepareDays(weatherEntity: WeatherEntity) =
        weatherEntity.daily.map { day ->
            DayListItem(
                dayStr = timeWorker.getWeekDay(day.getDate()),
                city = DEFAULT_LOCATION,
                day = day
            )
        }.toMutableList()

    fun itemClicked(item: DayListItem) {
        item.isSelected = true
        current?.isSelected = false
        current = getUpdatedCurrent(item)
        runAsync {
            mSharedFlow.emit(AppState.Success(HomeStateEntity(current, null, null)))
        }
    }

    private fun getUpdatedCurrent(item: DayListItem) =
        with(item) {
            DayListItem(
                dayStr = timeWorker.getDay(day.getDate()),
                city = city,
                day = day,
                isSelected = true
            )
        }

    companion object {
        private const val DEFAULT_LOCATION = "Запорожье"
    }
}