package com.a14mob.empresa.empresa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.a14mob.empresa.empresa.entity.Profissional
import com.a14mob.empresa.empresa.retrofit.RetroFitRestAPI
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import android.content.SharedPreferences
import java.util.jar.Manifest


class LoginActivity : AppCompatActivity() {

    var fieldCpf: String = ""

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Stetho.initializeWithDefaults(this);

        login.setOnClickListener {

            verificarLogin();
        }



    }

    fun verificarLogin(){

        var  liberado: Int = verificaProfissional();

    }

    fun carregarInformacoes(response: Profissional){

        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("cpf",fieldCpf.toString());

        val editor = getSharedPreferences("PROFISSIONAL", MODE_PRIVATE).edit()
        editor.putString("nome", response.nome)
        editor.putString("meta", response.meta)
        editor.putString("profissionalId", response.id.toString())
        editor.putString("foto", response.foto.toString())

        editor.putString("cpf",fieldCpf)

        editor.apply()


        startActivity(intent)
    }

    fun verificaProfissional(): Int {


        fieldCpf = "36131357870"//cpf.text.toString()

        Log.i("CPF",fieldCpf)

        api.buscarProfissional(fieldCpf)
                .enqueue(object : Callback<Profissional> {
                    override fun onResponse(call: Call<Profissional>?, response: Response<Profissional>?) {


                        if(response?.body()?.nome != null){
                            this@LoginActivity.carregarInformacoes(response?.body() as Profissional)

                        }else{
                            msgErro.text = "Profissional nao encontrado!"
                        }

                    }

                    override fun onFailure(call: Call<Profissional>?, t: Throwable?) {

                        msgErro.text = "Profissional nao encontrado!"
                    }
                })


        return 1;
    }
}
