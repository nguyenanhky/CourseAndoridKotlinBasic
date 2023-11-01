package kynv1.fsoft.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kynv1.fsoft.basic.R
import kynv1.fsoft.basic.databinding.CounterFragmentBinding
import kynv1.fsoft.basic.model.Counter
import kynv1.fsoft.basic.model.DataImplement
import kynv1.fsoft.basic.viewmodel.CounterViewModel

class CounterFragment : Fragment() {

    private var _binding: CounterFragmentBinding? = null
    val binding
        get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this)[CounterViewModel::class.java]
    }

    private val onDataUpdate: (value: Int) -> Unit = {
        showData(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = CounterFragmentBinding.inflate(inflater, container, false).also {
            arguments?.getString(ID_KEY)?.let { id ->
                viewModel.updateCurrentId(id)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onDataUpdate(onDataUpdate)
        setHasOptionsMenu(true)
        binding.plus1.setOnClickListener {
            viewModel.plusOne(onDataUpdate)
        }
        binding.plus2.setOnClickListener {
            viewModel.plusTwo(onDataUpdate)
        }
    }

    private fun showData(value: Int) {
        binding.textView.text = "$value"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.counter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionSave) {
            viewModel.saveOrUpdate {
                if(it){
                    activity?.onBackPressed()
                }
            }
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val ID_KEY = "id_key"
        fun newInstance(id: String? = null) = CounterFragment().apply {
            arguments = Bundle().apply {
                putString(ID_KEY, id)
            }
        }

        /**
         * optine 2
         */
//        fun newInstance(id: String? = null): CounterFragment {
//            val fragment = CounterFragment()
//            val bundle = Bundle()
//            bundle.putString(ID_KEY, id)
//            fragment.arguments = bundle
//            return fragment
//        }
    }
}