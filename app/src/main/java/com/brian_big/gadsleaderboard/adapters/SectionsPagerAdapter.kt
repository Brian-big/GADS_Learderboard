package com.brian_big.gadsleaderboard.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.brian_big.gadsleaderboard.R

private val TAB_TITLES = arrayOf(
        R.string.tab_learning_leaders,
        R.string.tab_skill_iq_leaders
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm) {
    private val fragmentList: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
       return fragmentList.size
    }
    fun addFragment(vararg  fragments: Fragment) {
        for (i in fragments){
            fragmentList.add(i)
        }
    }
}