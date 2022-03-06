package com.example.layer_presentation.main.home

import com.example.layer_data.location.LocationWorker
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
    private val timeWorker: TimeWorker,
    private val locationWorker: LocationWorker
) : BaseViewModel<HomeStateEntity>() {

    private var current: DayListItem? = null
    private var dayList: List<DayListItem>? = null
    private var hourList: List<HourListItem>? = null
    private var currentLocation = DEFAULT_LOCATION

    override fun onViewInit() {
        updateLocation()

        runAsync {
            mSharedFlow.emit(AppState.Loading())

            if (current == null || dayList == null || hourList == null) {
                updateWeatherData()
            }

            updateUI()
        }
    }

    private suspend fun updateUI() {
        val newList = mutableListOf<DayListItem>()
        newList.addAll(dayList!!)

        val homeStateEntity = HomeStateEntity(
            dayWithFullDate(current!!),
            dayList,
            hourList
        )

        mSharedFlow.emit(AppState.Success(homeStateEntity))
    }

    private suspend fun updateWeatherData() {
        val weatherEntity = getWeatherUseCase.execute(currentLocation)

        dayList = prepareDays(weatherEntity)
        hourList = prepareHours(weatherEntity)

        dayList?.get(0)?.isSelected = true
        current = dayList?.get(0)
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
                city = currentLocation,
                day = day
            )
        }.toMutableList()

    fun itemClicked(item: DayListItem) {
        // Code below creates copy of days list and replaces in copy changed elements
        // It is necessary because I use DiffUtil.
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

    private fun updateLocation() {
        locationWorker.getCityName()?.let { city ->
            // Api specific. (not L'viv, but Lviv)
            currentLocation = city.replace("'", "")
        }
    }

    fun findMyLocationPressed() = runAsync {
        updateLocation()
        updateWeatherData()
        updateUI()
    }


    companion object {
        private const val DEFAULT_LOCATION = "Запорожье"
    }
}