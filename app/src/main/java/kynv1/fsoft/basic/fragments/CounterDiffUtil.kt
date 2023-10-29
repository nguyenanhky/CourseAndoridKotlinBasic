package kynv1.fsoft.basic.fragments

import androidx.recyclerview.widget.DiffUtil
import kynv1.fsoft.basic.model.Counter

class CounterDiffUtil(private val oldItems: List<Counter>, private val newItems: List<Counter>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].isTheSameContent(newItems[newItemPosition])
    }
}