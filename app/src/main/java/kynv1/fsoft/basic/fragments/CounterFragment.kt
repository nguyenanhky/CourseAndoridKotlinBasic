package kynv1.fsoft.basic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kynv1.fsoft.basic.R

class CounterFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.counter_fragment, container, false)

    companion object {
        fun newInstance() = CounterFragment()
    }
}