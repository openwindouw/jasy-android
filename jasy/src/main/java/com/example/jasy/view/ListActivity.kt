package com.example.jasy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputType
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jasy.helpers.adapter.ApodListAdapter
import com.example.jasy.model.interactor.ApodInteractor
import com.example.jasy.model.ApodModel
import com.example.jasy.presenter.ListPresenter
import com.example.jasy.R
import com.example.jasy.helpers.Singleton
import com.example.jasy.helpers.extensions.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.list_activity.*
import java.io.Serializable
import java.util.*

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
        configureTextFields()
        setOnClickListenerFor(requestSearchButton)

        presenter.onCreate(this)

        val now = Date()
        presenter.getApods(now.formattedFirstDateOfCurrentMonth, now.formattedCurrentDate )
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setOnClickListenerFor(button: MaterialButton) {
        button.setOnClickListener {

            Singleton.apodList = null

            val startDateString = startDate.text.toString()
            val endDateString = endDate.text.toString()

            presenter.getApods(startDateString, endDateString)

            datesContainerLinearLayout.visibility = View.GONE
        }
    }

    private fun configureTextFields() {
        setOnFocusChangeListenerFor(startDate)
        setOnFocusChangeListenerFor(endDate)
    }

    private fun setOnFocusChangeListenerFor(editText: TextInputEditText) {
        editText.inputType = InputType.TYPE_NULL

        editText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val datePickerFragment = DatePickerFragment.newInstance { formattedDate ->
                    editText.setText(formattedDate)
                    editText.clearFocus()
                }

                datePickerFragment.show(supportFragmentManager, "datePicker")
            }
        }
    }

    private fun configureToolbar() {
        val toolbar = toolbar as MaterialToolbar
        toolbar.title = getString(R.string.app_name)

        toolbar.inflateMenu(R.menu.list_menu_options)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search -> {
                    if (datesContainerLinearLayout.visibility == View.VISIBLE) {
                        datesContainerLinearLayout.visibility = View.GONE
                    } else {
                        datesContainerLinearLayout.visibility = View.VISIBLE
                    }

                    true
                }
                else -> { true }
            }
        }
    }

    //Presenter's Methods
    override fun set(list: List<ApodModel>) {
        datesContainerLinearLayout.visibility = View.GONE

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

                return if (mod == 0 || mod == 1) 2
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
