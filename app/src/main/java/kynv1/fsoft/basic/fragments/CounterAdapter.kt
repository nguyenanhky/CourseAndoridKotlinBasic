package kynv1.fsoft.basic.fragments

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kynv1.fsoft.basic.databinding.ItemBinding
import kynv1.fsoft.basic.model.Counter


class CounterAdapter(val callback: (id: String) -> Unit) :
    RecyclerView.Adapter<CounterAdapter.Companion.CounterHolder>() {
    private val counters: MutableList<Counter> = mutableListOf()

    fun updateList(data: List<Counter>) {
        counters.clear()
        counters.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CounterHolder(binding, callback)

    }

    override fun getItemCount(): Int = counters.size

    override fun onBindViewHolder(holder: CounterHolder, position: Int) {
        holder.bind(counters[position])
    }

    fun moveItem(from: Int, to: Int) {
        val newValues = counters.swap(from,to)
        counters.clear()
        counters.addAll(newValues)
    }

    fun notifyMoveItem(from: Int, to: Int) {
        notifyItemChanged(from)
        notifyItemChanged(to)
    }

    companion object {
        class CounterHolder(private val binding: ItemBinding, val callback: (id: String) -> Unit) :
            RecyclerView.ViewHolder(binding.root) {
            fun bind(counter: Counter) {
                binding.value.text = "${counter.value}"
                binding.date.text = itemView.context.toDate(counter.dateInMillis)
                binding.cardItem.setOnClickListener {
                    callback(counter.id)
                }
            }
        }
    }
}

private fun Context.toDate(dataInMillis: Long): CharSequence? {
    val flags = DateUtils.FORMAT_ABBREV_TIME or DateUtils.FORMAT_SHOW_DATE
    return DateUtils.formatDateTime(this, dataInMillis, flags)
}

private fun <T> Iterable<T>.swap(from: Int, to: Int): Iterable<T> {
    val fromItem: T? = find { indexOf(it) == from }
    val toItem: T? = find { indexOf(it) == to }
    return mapIndexed { index, existing -> if (index == from && toItem != null) toItem else if (index == to && fromItem != null) fromItem else existing }
}