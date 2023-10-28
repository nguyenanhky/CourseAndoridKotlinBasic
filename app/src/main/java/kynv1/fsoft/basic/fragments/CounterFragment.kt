package kynv1.fsoft.basic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kynv1.fsoft.basic.R
import kynv1.fsoft.basic.databinding.CounterFragmentBinding

class CounterFragment : Fragment() {

    private var _binding: CounterFragmentBinding? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = CounterFragmentBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = CounterFragment()
    }
}