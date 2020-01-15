package com.example.jasy.presenter

import com.example.jasy.helpers.interfaces.BasePresenter
import com.example.jasy.helpers.interfaces.BaseView
import com.example.jasy.helpers.Singleton
import com.example.jasy.helpers.constants.DateFormatConstants
import com.example.jasy.model.interactor.ApodInteractorInterface
import com.example.jasy.model.ApodModel
import java.text.SimpleDateFormat
import java.util.*

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

    fun getPictureList(startDate: String, endDate: String) {
        if (Singleton.apodList == null) {
            view?.showActivityIndicator()
            interactor.getApods(startDate, endDate,false, {
                Singleton.apodList = it.sortedByDescending { apodModel ->
                    SimpleDateFormat(DateFormatConstants.default, Locale.US).parse(apodModel.date)
                }
                view?.hideActivityIndicator()
                view?.set(Singleton.apodList!!)
            }, {
                view?.hideActivityIndicator()
                view?.showError(it)
            })
        } else {
            view?.set(Singleton.apodList!!)
        }
    }
}