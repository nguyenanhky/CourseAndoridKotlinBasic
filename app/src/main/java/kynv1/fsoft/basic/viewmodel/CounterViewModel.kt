package kynv1.fsoft.basic.viewmodel

import androidx.lifecycle.ViewModel
import kynv1.fsoft.basic.model.Counter
import kynv1.fsoft.basic.model.DataImplement
import kynv1.fsoft.basic.model.DataInterface


class CounterViewModel(private val dataModel: DataInterface) : ViewModel() {
    var currentCounter: Counter? = null
    private var value = 0

    fun onDataUpdate(dataUpdate: (value: Int) -> Unit) = dataUpdate(value)

    fun updateCurrentId(id: String?) {
        currentCounter =dataModel.items.firstOrNull { it.id == id }
        value = currentCounter?.value ?: 0
    }

    private fun plus(value: Int, dataUpdate: (value: Int) -> Unit) {
        this.value += value
        dataUpdate(this.value)
    }

    fun plusOne(dataUpdate: (value: Int) -> Unit) = plus(1, dataUpdate)

    fun plusTwo(dataUpdate: (value: Int) -> Unit) = plus(2, dataUpdate)

    fun saveOrUpdate(callback: (isSuccess: Boolean) -> Unit) {
        val tempCounter = currentCounter
        val counter = tempCounter?.copy(value = value) ?: Counter(
            value = value,
            dateInMillis = System.currentTimeMillis()
        )
        dataModel.addOrUpdateItem(counter)
        callback(true)
    }

}