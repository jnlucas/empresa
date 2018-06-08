package com.a14mob.empresa.empresa

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.a14mob.empresa.empresa.entity.Avaliacao
import com.a14mob.empresa.empresa.entity.Profissional
import com.a14mob.empresa.empresa.retrofit.RetroFitRestAPI
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList



/**
 * Created by Joao .
 */
object PermissionUtils {


    var okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .build()


    var gson = GsonBuilder()
            .setLenient()

            .create()

    val retrofit = Retrofit.Builder()
            .baseUrl("http://api.14mob.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build()

    val api = retrofit.create<RetroFitRestAPI>(RetroFitRestAPI::class.java!!)

    //Solicita as permissoes

    fun validate(activity: Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            //valida permissão
            val ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!ok) {
                list.add(permission)
            }
        }
        if (list.isEmpty()) {
            //Todas Permissoes estão ok
            return true
        }

        //lista de permissoes que ainda falta acesso
        val newPermission = arrayOfNulls<String>(list.size)
        list.toArray(newPermission)
        //Solicita permissao
        ActivityCompat.requestPermissions(activity, newPermission, 1)
        return false
    }

    fun atualizaToken(cpf: String,token: String){


        api.sendTokenProfissional(cpf,token)
                .enqueue(object : Callback<Profissional> {
                    override fun onResponse(call: Call<Profissional>?, response: Response<Profissional>?) {
                    }

                    override fun onFailure(call: Call<Profissional>?, t: Throwable?) {
                    }
                })
    }

}