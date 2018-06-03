package com.a14mob.empresa.empresa.retrofit

import com.a14mob.empresa.empresa.entity.Profissional
import retrofit2.Call
import retrofit2.http.*

interface RetroFitRestAPI {
    @FormUrlEncoded()
    @POST("/profissional/api")
    fun buscarProfissional(@Field("cpf") cpf : String): Call<Profissional>

}
