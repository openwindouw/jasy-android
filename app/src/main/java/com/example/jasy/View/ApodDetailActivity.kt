package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jasy.Model.ApodModel
import com.example.jasy.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.apod_detail_activity.*

class ApodDetailActivity : AppCompatActivity() {

    private lateinit var apodModel: ApodModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apod_detail_activity)

        apodModel = intent.extras?.get("apodModel") as ApodModel
        apodDetailText.text = apodModel.explanation
        Picasso.get().load(apodModel.url).into(apodDetailImageView)
    }
}
