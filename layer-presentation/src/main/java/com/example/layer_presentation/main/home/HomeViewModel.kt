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
    private var dayList: List<DayListItem>? = null
    private var hourList: List<HourListItem>? = null

    override fun onViewInit() {
        runAsync {
            mSharedFlow.emit(AppState.Loading())

            if (current == null || dayList == null || hourList == null) {
                val weatherEntity = getWeatherUseCase.execute(DEFAULT_LOCATION)

                dayList = prepareDays(weatherEntity)
                hourList = prepareHours(weatherEntity)

                dayList?.get(0)?.isSelected = true
                current = dayList?.get(0)
            }

            val homeStateEntity = HomeStateEntity(dayWithFullDate(current!!), dayList, hourList)

            mSharedFlow.emit(AppState.Success(homeStateEntity))
        }
    }

    private fun dayWithFullDate(day: DayListItem) =
        day.copy(dayStr = timeWorker.getDay(day.day.getDate()))


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
        runAsync {
            if (!item.isSelected && current != null) {
                dayList?.let {
                    val new = item.copy(isSelected = true)
                    val newPos = dayList!!.lastIndexOf(item)

                    val prev = current!!.copy(isSelected = false)
                    val prevPos = dayList!!.lastIndexOf(current)

                    val newList = mutableListOf<DayListItem>()
                    newList.addAll(dayList!!)

                    newList[prevPos] = prev
                    newList[newPos] = new

                    current = new
                    mSharedFlow.emit(
                        AppState.Success(
                            HomeStateEntity(dayWithFullDate(current!!), newList, null)
                        )
                    )

                    dayList = newList
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_LOCATION = "Запорожье"
    }
}