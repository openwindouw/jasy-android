package com.example.jasy.View

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jasy.Helpers.Adapter.ApodListAdapter
import com.example.jasy.Helpers.Extensions.hide
import com.example.jasy.Helpers.Extensions.show
import com.example.jasy.Model.Interactor.ApodInteractor
import com.example.jasy.Model.ApodModel
import com.example.jasy.Presenter.ListPresenter
import com.example.jasy.R
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.android.synthetic.main.list_activity.*

class ListActivity : AppCompatActivity(), ListPresenter.View {

    companion object {
        const val APOD_MODEL = "apodModel"
    }

    private val presenter = ListPresenter(ApodInteractor())
    private lateinit var context: Context
    private var apodList: List<ApodModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        configureToolbar()

        context = this

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
                        Toast.makeText(context, "Searching...", Toast.LENGTH_LONG)
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
        listRecycleView.layoutManager = GridLayoutManager(this, 3)
        listRecycleView.adapter = adapter
    }

    override fun showActivityIndicator() {
        progressBar.show()
    }

    override fun hideActivityIndicator() {
        progressBar.hide()
    }

    override fun showError(message: String) {

    }
}
