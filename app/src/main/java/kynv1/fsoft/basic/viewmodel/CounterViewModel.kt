package kynv1.fsoft.basic.viewmodel

import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import kynv1.fsoft.basic.model.Counter
import kynv1.fsoft.basic.model.DataImplement
import kynv1.fsoft.basic.model.DataInterface


class CounterViewModel(private val dataModel: DataInterface) : ViewModel() {
    var currentCounter: Counter? = null
        private set
    var value = ObservableInt(0)

    fun updateCurrentId(id: String?) {
        currentCounter = dataModel.items.firstOrNull { it.id == id }
        value.set( currentCounter?.value ?: 0)
    }

    private fun plus(value: Int) {
        this.value.set(this.value.get()+value)
    }

    fun plusOne() = plus(1)

    fun plusTwo() = plus(2)

    fun saveOrUpdate(callback: (isSuccess: Boolean) -> Unit) {
        val tempCounter = currentCounter
        val counter = tempCounter?.copy(value = value.get()) ?: Counter(
            value = value.get(),
            dateInMillis = System.currentTimeMillis()
        )
        dataModel.addOrUpdateItem(counter)
        callback(true)
    }

}