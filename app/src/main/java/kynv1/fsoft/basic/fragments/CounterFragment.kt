package kynv1.fsoft.basic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kynv1.fsoft.basic.R
import kynv1.fsoft.basic.databinding.CounterFragmentBinding
import kynv1.fsoft.basic.model.Counter
import kynv1.fsoft.basic.model.DataImplement

class CounterFragment : Fragment() {

    private var _binding: CounterFragmentBinding? = null
    val binding
        get() = _binding!!

    private var value = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = CounterFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showData()
        setHasOptionsMenu(true)
        binding.plus1.setOnClickListener {
            value++
            showData()
        }
        binding.plus2.setOnClickListener {
            value += 2
            showData()
        }
    }

    private fun showData() {
        binding.textView.text = "$value"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.counter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionSave) {
            Toast.makeText(requireContext(), "Save action", Toast.LENGTH_SHORT).show()
            val counter = Counter(value = value, dateInMillis = System.currentTimeMillis())
            DataImplement.instance.addOrUpdateItem(counter)
            activity?.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}