package com.example.jasy.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jasy.Interactor.ApodInteractor
import com.example.jasy.Model.ApodModel
import com.example.jasy.Presenter.ListPresenter
import com.example.jasy.R

class ListActivity : AppCompatActivity(), ListPresenter.View {

    private val presenter = ListPresenter(ApodInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onCreate(this)
        presenter.getApods()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun set(list: List<ApodModel>) {
        Log.v("ListActivity", list.toString())
    }

    override fun showActivityIndicator() {

    }

    override fun hideActivityIndicator() {

    }

    override fun showError(message: String) {

    }
}
