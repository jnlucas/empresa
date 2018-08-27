package com.a14mob.empresa.empresa.firebase.fcm

import android.content.Context
import android.util.Log
import com.a14mob.empresa.empresa.PermissionUtils
import com.a14mob.empresa.empresa.entity.Avaliacao
import com.a14mob.empresa.empresa.entity.Profissional
import com.a14mob.empresa.empresa.retrofit.RetroFitRestAPI
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.gson.GsonBuilder
import com.orhanobut.hawk.Hawk
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by logonrm on 19/03/2018.
 */
class MeuFirebaseInstanceIdService: FirebaseInstanceIdService(){




    override fun onTokenRefresh() {
        super.onTokenRefresh()

        //Hawk.put("TOKENFIREBASE",FirebaseInstanceId.getInstance().token)


    }
}
