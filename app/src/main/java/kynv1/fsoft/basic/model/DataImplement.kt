package kynv1.fsoft.basic.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class DataImplement : DataInterface {
    private val _items = mutableListOf<Counter>()

    private object Holder {
        val dataController = DataImplement()
    }


    override val items: List<Counter>
        get() = _items

    private val _needUpdate = MutableLiveData<Boolean>(false)
    override var needUpdate: LiveData<Boolean> = _needUpdate


    override fun removeItem(counter: Counter) {
        _items.removeAll { it.id == counter.id }
    }

    override fun addOrUpdateItem(counter: Counter) {
        val index = _items.indexOf(counter)
        if (index in 0.._items.size) {
            _items[index] = counter
        } else {
            _items.add(counter)
        }
       _needUpdate.value = true
    }

    companion object {
        val instance = Holder.dataController
    }
}