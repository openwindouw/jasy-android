package com.example.jasy.Helpers.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.jasy.Model.ApodModel
import com.example.jasy.R

class ApodDetailPagerAdapter(val context: Context, val list: List<ApodModel>, val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {

    }

    override fun getCount(): Int {
       return list.size
    }

//    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val currentApod = list[position]
//        val layoutInflater = LayoutInflater.from(context)
//        val layout = layoutInflater.inflate(R.layout.apod_detail_view, container, false)
//        container.addView(layout)
//        return layout
//    }
//
//    override fun isViewFromObject(view: View, `object`: Any): Boolean {
//        return view == `object`
//    }
//
//    override fun getCount(): Int {
//        return list.size
//    }
//
//    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//        container.removeView(`object` as View)
//    }
}