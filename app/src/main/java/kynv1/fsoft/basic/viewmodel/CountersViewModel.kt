package kynv1.fsoft.basic.viewmodel

import androidx.lifecycle.ViewModel
import kynv1.fsoft.basic.model.DataInterface

class CountersViewModel(
    private val dataInterface: DataInterface,
) : ViewModel() {
    val items
        get() = dataInterface.items

    fun observer(callback: (update: Boolean) -> Unit) {
        dataInterface.needUpdate = callback
    }
}