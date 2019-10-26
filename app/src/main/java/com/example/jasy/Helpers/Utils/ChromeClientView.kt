package com.example.jasy.Helpers.Utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class CustomWebChromeClient(private val context: AppCompatActivity) : WebChromeClient() {
    private var customView: View? = null
    private var customViewCallback: CustomViewCallback? = null
    private var originalOrientation: Int = 0
    private var originalSystemUiVisibility: Int = 0

    override fun getDefaultVideoPoster(): Bitmap? {
        return BitmapFactory.decodeResource(context.applicationContext.resources, 2130837573)
    }
    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        customView?.let { onHideCustomView() }

        customView = view
        customView?.setBackgroundColor(Color.BLACK)
        originalSystemUiVisibility = context.window.decorView.systemUiVisibility
        originalOrientation = context.requestedOrientation
        customViewCallback = callback

        (context.window.decorView as FrameLayout).addView(customView,
            FrameLayout.LayoutParams(-1, -1))
        context.window.decorView.systemUiVisibility = 3846
    }

    override fun onHideCustomView() {
        (context.window.decorView as FrameLayout).removeView(customView)
        this.customView = null
        context.window.decorView.systemUiVisibility = this.originalSystemUiVisibility
        context.requestedOrientation = this.originalOrientation
        this.customViewCallback?.onCustomViewHidden()
        this.customViewCallback = null
    }
}
