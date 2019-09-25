package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jasy.Adapter.ApodListAdapter
import com.example.jasy.Interactor.ApodInteractor
import com.example.jasy.Model.ApodModel
import com.example.jasy.Presenter.ListPresenter
import com.example.jasy.R

class ListActivity : AppCompatActivity(), ListPresenter.View {

    private val presenter = ListPresenter(ApodInteractor())
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        presenter.onCreate(this)

        recyclerView = findViewById(R.id.apod_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        presenter.getApods()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun set(list: List<ApodModel>) {
        recyclerView.adapter = ApodListAdapter(list)
    }

    override fun showActivityIndicator() {

    }

    override fun hideActivityIndicator() {

    }

    override fun showError(message: String) {

    }
}
