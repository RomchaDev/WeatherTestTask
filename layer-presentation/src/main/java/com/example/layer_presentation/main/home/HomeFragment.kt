package com.example.layer_presentation.main.home

import android.view.View
import androidx.core.content.ContextCompat
import com.example.layer_domain.entity.list.ListItem
import com.example.layer_presentation.BR
import com.example.layer_presentation.R
import com.example.layer_presentation.core.list.MainListAdapter
import com.example.layer_presentation.core.main.BaseFragment
import com.example.layer_presentation.core.sky_state.IconFromIdGetter
import com.example.layer_presentation.databinding.FragmentHomeBinding
import com.example.layer_presentation.main.home.list_items.DayListItem
import com.example.layer_presentation.main.home.list_items.HourListItem
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeStateEntity, HomeViewModel>(
    R.layout.fragment_home
) {

    override val viewModel: HomeViewModel by viewModel()
    private val iconGetter: IconFromIdGetter by inject()
    private var selectedDayLayout: View? = null

    private var daysAdapter: MainListAdapter<DayListItem>? = null
    private var hoursAdapter: MainListAdapter<HourListItem>? = null

    override fun renderSuccess(data: HomeStateEntity) {
        super.renderSuccess(data)
        binding.current.current = data.current
        binding.current.iconGetter = iconGetter
        processLists(data.days, data.hours)
    }

    private fun processLists(
        days: List<DayListItem>?,
        hours: List<HourListItem>?
    ) {
        hours?.let {
            hoursAdapter?.submitList(hours) ?: initHoursList(hours)

        }

        days?.let {
            daysAdapter?.submitList(days) ?: initDaysList(days)
        }
    }

    private fun initDaysList(days: List<DayListItem>) {
        val adapter = MainListAdapter<DayListItem>(
            mapOf(ListItem.DEFAULT_ITEM_VIEW_TYPE to R.layout.fragment_day)
        ) { binding, item ->
            binding.setVariable(BR.iconGetter, iconGetter)

            setSelected(item.isSelected, binding.root)
            //if (item.isSelected) selectedDayLayout = binding.root

            binding.root.setOnClickListener {
                viewModel.itemClicked(item)
            }
        }

        val rv = binding.days.rvDays

        daysAdapter = adapter
        rv.adapter = adapter
        adapter.submitList(days)
    }

    private fun setSelected(isSelected: Boolean, view: View) {
        val drawable =
            if (isSelected) ContextCompat.getDrawable(requireContext(), R.color.selection)
            else ContextCompat.getDrawable(requireContext(), R.color.white)

        view.background = drawable
    }

    private fun initHoursList(hours: List<HourListItem>) {
        val adapter = MainListAdapter<HourListItem>(
            mapOf(ListItem.DEFAULT_ITEM_VIEW_TYPE to R.layout.fragment_hour)
        ) { binding, _ ->
            binding.setVariable(BR.iconGetter, iconGetter)
        }

        hoursAdapter = adapter
        binding.current.rvHours.adapter = adapter
        adapter.submitList(hours)
    }
}