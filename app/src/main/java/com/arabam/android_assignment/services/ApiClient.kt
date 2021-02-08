package com.arabam.android_assignment.services.retrofit

import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.models.AdvertDetails
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val BASE_URL = "http://sandbox.arabamd.com/api/v1/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(Api::class.java)

    fun getAdvertList(sort:Int?=0,sortDirection:Int?=1,take:Int?=5): Single<List<Advert>> {
        return api.getAdverts(sort.toString(),sortDirection.toString(),take.toString())
    }

    fun getAdvertDetails(id:Int): Single<AdvertDetails> {
        return api.getAdvertDetails(id)
    }
}
