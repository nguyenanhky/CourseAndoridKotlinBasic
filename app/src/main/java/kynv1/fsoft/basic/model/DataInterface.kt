package kynv1.fsoft.basic.model


interface DataInterface {
    val items: List<Counter>
    fun addOrUpdateItem(counter: Counter)
    fun removeItem(counter: Counter)
}