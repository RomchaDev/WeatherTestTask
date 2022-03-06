package com.example.layer_presentation.main.home

import androidx.recyclerview.widget.RecyclerView
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

    override fun renderSuccess(data: HomeStateEntity) {
        super.renderSuccess(data)
        binding.current?.current = data.current
        binding.current?.iconGetter = iconGetter
        initLists(data.days, data.hours)
    }

    private fun initLists(
        days: List<DayListItem>,
        hours: List<HourListItem>
    ) {
        binding.days?.rvDays?.let { rv ->
            val adapter = initList<DayListItem>(R.layout.fragment_day, rv)
            adapter.submitList(days)
        }

        binding.current?.rvHours?.let { rv ->
            val adapter = initList<HourListItem>(R.layout.fragment_hour, rv)
            adapter.submitList(hours)
        }
    }

    fun <T : ListItem<T>> initList(itemLayoutId: Int, rv: RecyclerView): MainListAdapter<T> {
        val adapter = MainListAdapter<T>(
            mapOf(ListItem.DEFAULT_ITEM_VIEW_TYPE to itemLayoutId)
        ) { binding, _ ->
            binding.setVariable(BR.iconGetter, iconGetter)
        }

        rv.adapter = adapter
        return adapter
    }
}