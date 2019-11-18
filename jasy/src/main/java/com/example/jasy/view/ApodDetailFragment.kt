package com.example.jasy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.jasy.helpers.utils.CustomWebChromeClient
import com.example.jasy.model.ApodModel
import com.example.jasy.R
import com.squareup.picasso.Picasso

private const val ARG_APOD_MODEL = "apodmodel"
private const val MEDIA_TYPE_IMAGE = "image"

class ApodDetailFragment : Fragment() {
    private var apodModel: ApodModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            apodModel = it.getSerializable(ARG_APOD_MODEL) as? ApodModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView = inflater.inflate(R.layout.apod_detail_view, container, false)
        val pictureImageView = layoutView.findViewById(R.id.apodDetailImageView) as ImageView
        val webView = layoutView.findViewById(R.id.apodDetailWebView) as WebView
        val textDetail = layoutView.findViewById(R.id.apodDetailText) as TextView

        if (apodModel?.media_type == MEDIA_TYPE_IMAGE) {
            pictureImageView.isVisible = true
            Picasso.get().load(apodModel?.url).into(pictureImageView)
        } else {
            webView.isVisible = true
            webView.webChromeClient = CustomWebChromeClient(this.activity as AppCompatActivity)
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(apodModel?.url)
        }

        textDetail.text = apodModel?.explanation

        layoutView.background = resources.getDrawable(R.drawable.layout_background)

        return layoutView
    }

    companion object {

        @JvmStatic
        fun newInstance(apodModel: ApodModel) =
            ApodDetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_APOD_MODEL, apodModel)

                }
            }
    }
}
