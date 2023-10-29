package kynv1.fsoft.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import kynv1.fsoft.basic.NavigationInterface
import kynv1.fsoft.basic.databinding.CountersFragmentBinding
import kynv1.fsoft.basic.model.generateFakeData
import kynv1.fsoft.basic.utils.Logger
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class ListCounterFragment : Fragment() {
    private var _binding: CountersFragmentBinding? = null
    val binding
        get() = _binding!!

    private val navigationController by lazy {
        requireActivity() as? NavigationInterface
    }

    private val adapter by lazy {
        CounterAdapter {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
    private val itemTouchHelper by lazy {
        val simpleItemTouchCallBack =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN , 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    Logger.lod("onMove")
                    val adapter = recyclerView.adapter as CounterAdapter
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    adapter.moveItem(from,to)
                    adapter.notifyMoveItem(from,to)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    Logger.lod("onSwiped")
                }
            }
        ItemTouchHelper(simpleItemTouchCallBack)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = CountersFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.adapter = adapter
        adapter.updateList(generateFakeData())
        itemTouchHelper.attachToRecyclerView(binding.recycler)
        binding.newCounter.setOnClickListener {
            navigationController?.navigateTo(CounterFragment.newInstance())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = ListCounterFragment()
    }
}