package com.test.newconcepts.data.api

import com.test.newconcepts.data.model.Questions
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/2.2/questions?")
    fun getQuestions(
        @Query("order") order: String?,
        @Query("sort") sort: String?,
        @Query("site") site: String?
    ): Observable<Questions>



}