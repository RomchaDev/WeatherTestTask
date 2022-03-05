package com.example.layer_presentation.core.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.layer_domain.entity.list.ListItem

/**
 * The adapter that has to be used for the each recycler view in app
 *
 * @param itemLayoutId - the map in which should be presented item layouts and
 * their view types.
 *
 * @param bind - the code, that should be executed when the view is bound to
 * the viewHolder
 * */
open class MainListAdapter<I : ListItem<I>>(
    private val itemLayoutId: Map<Int, Int>,
    private val bind: ((ViewDataBinding, data: I) -> Unit)? = null
) : ListAdapter<I, MainListAdapter<I>.BaseViewHolder>(BaseDiffUtilCallback<I>()) {

    inner class BaseViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        /**
         * The method that injects data into xml and calls
         * bind method from adapter.
         *
         * @param data - data that will be injected into xml
         * */
        fun bind(data: I) {
            binding.setVariable(BR.data, data)
            bind?.invoke(binding, data)
/*            val subClasses = data::class.sealedSubclasses
            val subClassFound = subClasses.find { it.isInstance(data) }

            try {
                val dataCasted = subClassFound!!.cast(data)
                binding.setVariable(BR.data, dataCasted)
            } catch (e: Exception) {
                try {
                    binding.setVariable(BR.data, data)
                } catch (e: Exception) {
                    Log.d(TAG, "bind: cannot inject a variable to xml")
                }
            }

            bind?.invoke(binding, data)*/
        }
    }

    override fun getItemViewType(position: Int) =
        currentList[position].getViewType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            itemLayoutId[viewType]!!,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        const val TAG = "BASE_ADAPTER"
    }
}