package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jasy.Helpers.Adapter.ApodListAdapter
import com.example.jasy.Helpers.Extensions.hide
import com.example.jasy.Helpers.Extensions.show
import com.example.jasy.Model.Interactor.ApodInteractor
import com.example.jasy.Model.ApodModel
import com.example.jasy.Presenter.ListPresenter
import com.example.jasy.R
import kotlinx.android.synthetic.main.list_activity.*

class ListActivity : AppCompatActivity(), ListPresenter.View {

    private val presenter = ListPresenter(ApodInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        presenter.onCreate(this)
        listRecycleView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        presenter.getApods()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun set(list: List<ApodModel>) {
        listRecycleView.adapter = ApodListAdapter(list)
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
