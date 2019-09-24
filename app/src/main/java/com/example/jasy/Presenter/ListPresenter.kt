package com.example.jasy.Presenter

import com.example.jasy.Helpers.BasePresenter
import com.example.jasy.Helpers.BaseView
import com.example.jasy.Interactor.ApodInteractorInterface
import com.example.jasy.Model.ApodModel

class ListPresenter(private val interactor: ApodInteractorInterface): BasePresenter<ListPresenter.View> {

    interface View: BaseView {
        fun set(list: List<ApodModel>)
    }

    private var view: View? = null

    override fun onCreate(view: View) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }

    fun getApods() {
        interactor.getApods("2019-02-01", "2019-02-28",false, {
            view?.set(it)
        }, {
            view?.showError(it)
        })
    }
}