package com.example.jasy.Helpers.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.jasy.Model.ApodModel
import com.example.jasy.View.ApodDetailFragment

private const val MAX_VALUE = 200

class ApodDetailPagerAdapter(val list: List<ApodModel>, fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return ApodDetailFragment.newInstance((list[position % list.size]))
    }

    override fun getCount(): Int {
       return list.size * MAX_VALUE
    }
}