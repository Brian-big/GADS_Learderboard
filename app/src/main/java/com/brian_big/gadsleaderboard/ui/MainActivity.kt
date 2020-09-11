package com.brian_big.gadsleaderboard.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.brian_big.gadsleaderboard.R
import com.brian_big.gadsleaderboard.adapters.SectionsPagerAdapter
import com.brian_big.gadsleaderboard.ui.fragments.TopIQFragment
import com.brian_big.gadsleaderboard.ui.fragments.TopLearnersFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewPager()

        submitBtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java))
        })
    }

    private fun setUpViewPager() {
        val topLearnersFragment = TopLearnersFragment()
        val topIQFragment = TopIQFragment()

        val vpAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        vpAdapter.addFragment(topLearnersFragment, topIQFragment)

        val vp = findViewById<ViewPager>(R.id.viewPager)
        vp.adapter = vpAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setupWithViewPager(vp)
    }
}