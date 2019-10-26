package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.isVisible
import com.example.jasy.Model.ApodModel
import com.example.jasy.R
import com.google.android.material.appbar.MaterialToolbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.apod_detail_activity.*
import com.example.jasy.Helpers.Utils.CustomWebChromeClient

class ApodDetailActivity : AppCompatActivity() {

    companion object {
        private const val MEDIA_TYPE_IMAGE = "image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apod_detail_activity)

        val apodModel = intent.extras?.get(ListActivity.APOD_MODEL) as? ApodModel

        apodModel?.let {
            configure(it)
            configureToolbar(it)
        }
    }

    private fun configure(apod: ApodModel) {
//        if (apod.media_type == MEDIA_TYPE_IMAGE) {
//            apodDetailImageView.isVisible = true
//            Picasso.get().load(apod.url).into(apodDetailImageView)
//        } else {
//            apodDetailWebView.isVisible = true
//            apodDetailWebView.webChromeClient = CustomWebChromeClient(this)
//            apodDetailWebView.settings.javaScriptEnabled = true
//            apodDetailWebView.loadUrl(apod.url)
//        }
//
//        apodDetailText.text = apod.explanation

    }

    private fun configureToolbar(apod: ApodModel) {
        val toolbar = apodDetailToolbar as MaterialToolbar

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.title = apod.title
        toolbar.subtitle = apod.date
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}