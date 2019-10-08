package com.example.jasy.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.jasy.Model.ApodModel
import com.example.jasy.R
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.apod_detail_activity.*

class ApodDetailActivity : AppCompatActivity() {

    private lateinit var apodModel: ApodModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apod_detail_activity)

        apodModel = intent.extras?.get(ListActivity.APOD_MODEL) as ApodModel
        apodDetailText.text = apodModel.explanation
        Picasso.get().load(apodModel.url).into(apodDetailImageView)

        val toolbar = apodDetailToolbar as MaterialToolbar

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.title = apodModel.title
        toolbar.subtitle = apodModel.date
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
