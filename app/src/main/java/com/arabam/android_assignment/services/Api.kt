package com.arabam.android_assignment.services.retrofit

import com.arabam.android_assignment.models.Advert
import com.arabam.android_assignment.models.AdvertDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("listing")
    fun getAdverts(
        @Query("sort") sort: String,
        @Query("sortDirection") sortDirection: String,
        @Query("take") take: String
    ): Single<List<Advert>>

    @GET("detail")
    fun getAdvertDetails(
        @Query("id") id: Int
    ): Single<AdvertDetails>
}