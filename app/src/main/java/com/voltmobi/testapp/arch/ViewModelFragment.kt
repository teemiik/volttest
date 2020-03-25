package com.voltmobi.testapp.arch

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class ViewModelFragment<VM : ViewModel> : BaseFragment() {

    protected abstract fun getViewModelClass(): Class<VM>

    protected abstract fun getFactory(): ViewModelProvider.Factory

    protected open fun getViewModel(): VM {
        return ViewModelProviders.of(this, getFactory())
                .get(getViewModelClass())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getViewModel().onSaveState(outState)
    }

    override fun onViewStateRestored(outState: Bundle?) {
        super.onViewStateRestored(outState)
        outState?.let { getViewModel().onRestoreState(it) }
    }

    override fun onResume() {
        super.onResume()
        onUiAttach()
        getViewModel().onUiAttach()
    }

    override fun onPause() {
        super.onPause()
        getViewModel().onUiDetach()
        onUiDetach()
    }

    protected open fun onUiAttach() { }

    protected open fun onUiDetach() { }

}