package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.jasy.Helpers.Adapter.ApodDetailPagerAdapter
import com.example.jasy.Helpers.Singleton
import com.example.jasy.Model.ApodModel
import com.example.jasy.R
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.apod_detail_activity.*
import kotlin.math.absoluteValue


class ApodDetailActivity : AppCompatActivity() {
    private lateinit var pagerAdapter: ApodDetailPagerAdapter
    private lateinit var selectedApod: ApodModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apod_detail_activity)

        selectedApod = intent.getSerializableExtra(ListActivity.APOD_MODEL) as ApodModel

        Singleton.apodList?.let {
            pagerAdapter = ApodDetailPagerAdapter(it, supportFragmentManager)
            viewPager.adapter = pagerAdapter

            viewPager.currentItem = it.indexOf(selectedApod)
            viewPager.clipToPadding = false

            val sidePadding = resources.getDimension(R.dimen.view_pager_padding).toInt()
            val topPadding = resources.getDimension(R.dimen.view_pager_padding_top).toInt()
            val itemsMargin = resources.getDimension(R.dimen.view_pager_margin_items).toInt()

            viewPager.setPadding(sidePadding, topPadding, sidePadding, 0)
            viewPager.pageMargin = itemsMargin

            viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) { }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) { }

                override fun onPageSelected(position: Int) {
                    setToolbarTitle(it[position % it.size])
                }
            })
        }

        configureToolbar()
        setToolbarTitle(selectedApod)
    }


    private fun configureToolbar() {
        val toolbar = apodDetailToolbar as MaterialToolbar

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setToolbarTitle(apod: ApodModel) {
        val toolbar = apodDetailToolbar as MaterialToolbar
        toolbar.title = apod.title
        toolbar.subtitle = apod.date
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}