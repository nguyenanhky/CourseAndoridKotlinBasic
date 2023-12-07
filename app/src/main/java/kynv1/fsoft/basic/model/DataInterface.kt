package kynv1.fsoft.basic.model

import androidx.lifecycle.LiveData


interface DataInterface {
    val items: List<Counter>

    var needUpdate:LiveData<Boolean>
    fun addOrUpdateItem(counter: Counter)
    fun removeItem(counter: Counter)
}