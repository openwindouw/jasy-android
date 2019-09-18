package com.example.jasy.Interactor

import com.example.jasy.Model.ApodModel
import com.example.jasy.Model.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ApodInteractorInterface {
    fun getApod(date: String, isHD: Boolean, apiKey: String, onSuccess: (ApodModel) -> Unit, onError: ((String) -> Unit)?)
}

class ApodInteractor: ApodInteractorInterface {
    override fun getApod(date: String, isHD: Boolean, apiKey: String, onSuccess: (ApodModel) -> Unit, onError: ((String) -> Unit)?) {
        ApodService.shared
            .getApod(false, "", "j3QsWa596qx2WxAMvZtxJAM4oH55JiV9mbIxO2Ng")
            .enqueue(object: Callback<ApodModel> {
                override fun onFailure(call: Call<ApodModel>, t: Throwable) {
                    onError?.let { it(t.localizedMessage) }
                }

                override fun onResponse(call: Call<ApodModel>, response: Response<ApodModel>) {
                    if (response.code() != 200) return
                    val apodModel = response.body() ?: return
                    onSuccess(apodModel)
                }
            })
    }
}