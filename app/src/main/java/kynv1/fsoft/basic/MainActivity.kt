package kynv1.fsoft.basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kynv1.fsoft.basic.fragments.ListCounterFragment

class MainActivity : AppCompatActivity(), NavigationInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerSimple, ListCounterFragment.newInstance())
                .commit()
        }
    }

    override fun navigateTo(fragment: Fragment) {
        if (supportFragmentManager.fragments.size == 1) {
            supportFragmentManager.beginTransaction()
                .add(R.id.containerSimple, fragment).commitNow()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.fragments.last()).commitNow()
        } else {
            super.onBackPressed()
        }
    }
}