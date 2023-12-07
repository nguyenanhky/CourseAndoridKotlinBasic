package kynv1.fsoft.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.END
import androidx.recyclerview.widget.ItemTouchHelper.START
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView
import kynv1.fsoft.basic.NavigationInterface
import kynv1.fsoft.basic.databinding.CountersFragmentBinding
import kynv1.fsoft.basic.model.DataImplement
import kynv1.fsoft.basic.utils.Logger
import kynv1.fsoft.basic.viewmodel.CountersViewModel
import kynv1.fsoft.basic.viewmodel.createViewModel

class ListCounterFragment : Fragment() {

    private lateinit var binding:CountersFragmentBinding
    private val viewModel by lazy {
        createViewModel {
            CountersViewModel(DataImplement.instance)
        }
    }
    private val navigationController by lazy {
        requireActivity() as? NavigationInterface
    }

    private val adapter by lazy {
        CounterAdapter {
            onItemClick(it)
        }
    }
    private val onItemClick:(id:String)->Unit = {id->
        navigationController?.navigateTo(CounterFragment.newInstance(id))
    }
    private val itemTouchHelper by lazy {
        val simpleItemTouchCallBack =
            object : ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder,
                ): Boolean {
                    Logger.lod("onMove")
                    val adapter = recyclerView.adapter as CounterAdapter
                    val from = viewHolder.adapterPosition
                    val to = target.adapterPosition
                    adapter.moveItem(from, to)
                    adapter.notifyMoveItem(from, to)
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
    ): View = CountersFragmentBinding.inflate(inflater,container,false).apply {
        binding = this
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodle = viewModel
        binding.lifecycleOwner = this
        binding.recycler.adapter = adapter
        viewModel.needUpdate.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"${it.toString()}",Toast.LENGTH_SHORT).show()
            adapter.updateList(DataImplement.instance.items)
        })
        itemTouchHelper.attachToRecyclerView(binding.recycler)

        binding.newCounter.setOnClickListener {
            navigationController?.navigateTo(CounterFragment.newInstance())
        }
    }

    companion object {
        fun newInstance() = ListCounterFragment()
    }
}