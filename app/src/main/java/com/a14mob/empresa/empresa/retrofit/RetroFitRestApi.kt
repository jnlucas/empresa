package com.a14mob.empresa.empresa.retrofit

import com.a14mob.empresa.empresa.entity.Profissional
import com.a14mob.empresa.empresa.entity.Score
import retrofit2.Call
import retrofit2.http.*

interface RetroFitRestAPI {
    @FormUrlEncoded()
    @POST("/profissional/api")
    fun buscarProfissional(@Field("cpf") cpf : String): Call<Profissional>



    @GET("/score/{profissional}/{meta}/api")
    fun scoreProfissional(@Path("profissional") profissional : Int,
                          @Path("meta") meta : Int): Call<List<Score>>

}
