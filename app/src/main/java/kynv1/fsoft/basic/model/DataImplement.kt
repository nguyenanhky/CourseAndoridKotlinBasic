package kynv1.fsoft.basic.model


class DataImplement: DataInterface {
    private val _items = mutableListOf<Counter>()

    private object Holder {
        val dataController = DataImplement()
    }


    override val items: List<Counter>
        get() = _items

    override var needUpdate: (willUpdate: Boolean) -> Unit = {}


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
        needUpdate(true)
    }

    companion object {
        val instance = Holder.dataController
    }
}