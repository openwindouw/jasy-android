package com.example.jasy.Interactor

import com.example.jasy.Helpers.Constants.NASAConstants
import com.example.jasy.Model.ApodModel
import com.example.jasy.Model.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ApodInteractorInterface {
    fun getApod(date: String, isHD: Boolean, onSuccess: (ApodModel) -> Unit, onError: ((String) -> Unit)?)
    fun getApods(initialDate: String, endDate: String, isHD: Boolean, onSuccess: (List<ApodModel>) -> Unit, onError: ((String) -> Unit)?)
}

class ApodInteractor: ApodInteractorInterface {
    override fun getApod(
        date: String,
        isHD: Boolean,
        onSuccess: (ApodModel) -> Unit,
        onError: ((String) -> Unit)?
    ) {
        ApodService.shared
            .getApod(false, "", NASAConstants.apiKey)
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

    override fun getApods(
        initialDate: String,
        endDate: String,
        isHD: Boolean,
        onSuccess: (List<ApodModel>) -> Unit,
        onError: ((String) -> Unit)?
    ) {
        ApodService.shared
            .getApods(false, initialDate, endDate, NASAConstants.apiKey)
            .enqueue(object: Callback<List<ApodModel>> {
                override fun onFailure(call: Call<List<ApodModel>>, t: Throwable) {
                    onError?.let { it(t.localizedMessage) }
                }

                override fun onResponse(call: Call<List<ApodModel>>, response: Response<List<ApodModel>>) {
                    if (response.code() != 200) return
                    val apodList = response.body() ?: return
                    onSuccess(apodList)
                }
            })
    }
}