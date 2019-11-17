package com.example.jasy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jasy.helpers.adapter.ApodListAdapter
import com.example.jasy.helpers.extensions.hide
import com.example.jasy.helpers.extensions.show
import com.example.jasy.model.interactor.ApodInteractor
import com.example.jasy.model.ApodModel
import com.example.jasy.presenter.ListPresenter
import com.example.jasy.R
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.list_activity.*

class ListActivity : AppCompatActivity(), ListPresenter.View {

    companion object {
        const val APOD_MODEL = "apodModel"
    }

    private val presenter = ListPresenter(ApodInteractor())
    private var apodList: List<ApodModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        configureToolbar()

        presenter.onCreate(this)
        presenter.getApods()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun configureToolbar() {
        val toolbar = toolbar as MaterialToolbar
        toolbar.title = "JASY"

        toolbar.inflateMenu(R.menu.list_menu_options)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    runOnUiThread {
                        Toast.makeText(this, "Searching...", Toast.LENGTH_LONG)
                    }
                    true
                }
                else -> { true }
            }
        }
    }

    //Presenter's Methods
    override fun set(list: List<ApodModel>) {
        apodList = list

        val adapter = ApodListAdapter(list) {
            val i = Intent(this, ApodDetailActivity::class.java)
            i.putExtra(APOD_MODEL, it)
            startActivity(i)
        }

        val gridLayoutManager = GridLayoutManager(this, 3)
        gridLayoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position == 0) return 3

                val mod = position % 4

                return if (position < 4) 1
                else if (mod == 0 || mod == 1) 2
                else 1
            }
        }
        listRecycleView.layoutManager = gridLayoutManager
        listRecycleView.adapter = adapter
    }

    override fun showActivityIndicator() {
        progressBar.show()
    }

    override fun hideActivityIndicator() {
        progressBar.hide()
    }

    override fun showError(message: String) {
        Log.d("", message)
    }
}
