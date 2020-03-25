package com.voltmobi.testapp.arch

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren

abstract class ViewModel : ViewModel() {

    protected val uiScope = CoroutineScope(Dispatchers.Default)

    open fun onSaveState(bundle: Bundle) { }

    open fun onRestoreState(bundle: Bundle) { }

    open fun onUiAttach(){}

    open fun onUiDetach() {
        uiScope.coroutineContext.cancelChildren()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
        uiScope.cancel()
    }
}