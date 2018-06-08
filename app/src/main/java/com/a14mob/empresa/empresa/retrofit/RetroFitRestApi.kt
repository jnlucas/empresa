package com.a14mob.empresa.empresa.retrofit

import com.a14mob.empresa.empresa.entity.Avaliacao
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

    @GET("/score/{profissional}/{meta}/api-avaliacao")
    fun scoreProfissionalAvaliacao(@Path("profissional") profissional : Int,
                          @Path("meta") meta : Int): Call<List<Avaliacao>>

    @FormUrlEncoded()
    @POST("/profissional/apiToken")
    fun sendTokenProfissional(@Field("cpf") cpf : String,@Field("token") token : String): Call<Profissional>

}
