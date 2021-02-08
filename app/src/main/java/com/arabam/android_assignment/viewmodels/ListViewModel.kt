package com.arabam.android_assignment.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.services.LocalDatabase
import com.arabam.android_assignment.services.retrofit.ApiService
import com.arabam.android_assignment.views.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {
    private val apiService = ApiService()
    private val disposable = CompositeDisposable()


    val advertList = MutableLiveData<List<Advert>>()
    val advertError = MutableLiveData<Boolean>()
    val advertLoading = MutableLiveData<Boolean>()


    fun getAdvertList() {
        if (getDataFromLocal() == null) {
            getDataFromAPI()
        }
    }

    private fun getDataFromLocal(): List<Advert>? {
        launch {
            val list = LocalDatabase(getApplication()).roomDao().getAdvertList()
            updateList(list)
        }

        return advertList.value
    }

    fun getDataFromAPI() {
        advertLoading.value = true
        disposable.add(
            apiService.getAdvertList(0, 1, 30).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Advert>>() {
                    override fun onSuccess(t: List<Advert>) {
                        saveLocal(t)
                    }

                    override fun onError(e: Throwable) {
                        advertError.value = true
                        advertLoading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun updateList(list: List<Advert>) {
        advertList.value = list
        advertError.value = false
        advertLoading.value = false
    }

    private fun saveLocal(list: List<Advert>) {
        launch {
            val dao = LocalDatabase(getApplication()).roomDao()
            dao.deleteAdvertList()
            var listLong = dao.insertAll(*list.toTypedArray())

            for (i in list.indices) {
                list[i].uuid = listLong[i].toInt()
            }
            updateList(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        this.disposable.clear()
    }

}