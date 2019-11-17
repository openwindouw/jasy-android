package com.example.jasy.presenter

import com.example.jasy.helpers.interfaces.BasePresenter
import com.example.jasy.helpers.interfaces.BaseView
import com.example.jasy.helpers.Singleton
import com.example.jasy.model.interactor.ApodInteractorInterface
import com.example.jasy.model.ApodModel

class ListPresenter(private val interactor: ApodInteractorInterface):
    BasePresenter<ListPresenter.View> {

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
        if (Singleton.apodList == null) {
            view?.showActivityIndicator()
            interactor.getApods("2019-02-01", "2019-02-28",false, {
                Singleton.apodList = it
                view?.hideActivityIndicator()
                view?.set(it)
            }, {
                view?.hideActivityIndicator()
                view?.showError(it)
            })
        } else {
            view?.set(Singleton.apodList!!)
        }
    }
}