package kynv1.fsoft.basic.model


interface DataInterface {
    val items: List<Counter>

    var needUpdate:(willUpdate:Boolean)->Unit
    fun addOrUpdateItem(counter: Counter)
    fun removeItem(counter: Counter)
}