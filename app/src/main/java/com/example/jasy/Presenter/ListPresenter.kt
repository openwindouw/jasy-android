package com.example.jasy.Presenter

import com.example.jasy.Helpers.Interfaces.BasePresenter
import com.example.jasy.Helpers.Interfaces.BaseView
import com.example.jasy.Helpers.Singleton
import com.example.jasy.Model.Interactor.ApodInteractorInterface
import com.example.jasy.Model.ApodModel

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