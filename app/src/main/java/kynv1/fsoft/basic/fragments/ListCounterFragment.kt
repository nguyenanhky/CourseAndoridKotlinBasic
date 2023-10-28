package kynv1.fsoft.basic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kynv1.fsoft.basic.NavigationInterface
import kynv1.fsoft.basic.R
import kynv1.fsoft.basic.databinding.CountersFragmentBinding

class ListCounterFragment : Fragment() {
    private var _binding: CountersFragmentBinding? = null
    val binding
        get() = _binding!!

    private val navigationController by lazy {
        requireActivity() as? NavigationInterface
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