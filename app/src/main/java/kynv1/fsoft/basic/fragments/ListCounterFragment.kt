package kynv1.fsoft.basic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kynv1.fsoft.basic.NavigationInterface
import kynv1.fsoft.basic.R

class ListCounterFragment: Fragment() {

    private val navigationController by lazy {
        requireActivity() as? NavigationInterface
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.counters_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        newCounter.setOnClickListener {
//            navigationController?.navigateTo(CounterFragment.newInstance())
//        }
    }

    companion object {
        fun newInstance() = ListCounterFragment()
    }
}