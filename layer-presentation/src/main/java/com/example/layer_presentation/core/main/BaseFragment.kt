package com.example.layer_presentation.core.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.layer_presentation.core.view.launchWhenStarted

abstract class BaseFragment<VB : ViewDataBinding, D : AppStateEntity, VM : BaseViewModel<D>>(
    private val layoutID: Int
) : Fragment() {

    private var bindingNullable: VB? = null
    protected val binding get() = bindingNullable!!
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil.inflate<VB>(
        inflater,
        layoutID,
        container,
        false
    ).apply { bindingNullable = this }
        .root

    protected open fun renderData(state: AppState<D>) {
        when (state) {
            is AppState.Success -> renderSuccess(state.data)
            is AppState.Error -> renderError(state.error)
            is AppState.Message -> renderMessage(state.message)
            is AppState.Loading -> setLoading(true)
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLiveData.launchWhenStarted(lifecycleScope) { state ->
            renderData(state)
        }

        viewModel.onViewInit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingNullable = null
    }

    protected open fun renderSuccess(data: D) {
        setLoading(false)
    }

    protected open fun renderError(error: Throwable) {
        setLoading(false)
        error.message?.let { showMessage(it) }
    }

    protected open fun renderMessage(message: String) {
        setLoading(false)
        showMessage(message)
    }

    //Can be empty
    protected open fun setLoading(isLoading: Boolean) {
    }

    protected open fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }
}