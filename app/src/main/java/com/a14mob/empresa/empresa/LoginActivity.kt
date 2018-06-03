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

        if(liberado == 1){
            //carregarInformacoes()

        }else{
            msgErro.text = "Profissional nao encontrado!"
        }

    }

    fun carregarInformacoes(){

        val intent = Intent(this,MainActivity::class.java)
        //intent.putExtra("PONTUACAO",tvPontuacao.text.toString());
        startActivity(intent)
    }

    fun verificaProfissional(): Int {

        Toast.makeText(this@LoginActivity,"iniciando",Toast.LENGTH_LONG).show()
        fieldCpf = cpf.text.toString()

        Log.i("CPF",fieldCpf)

        api.buscarProfissional("36131357870")
                .enqueue(object : Callback<Profissional> {
                    override fun onResponse(call: Call<Profissional>?, response: Response<Profissional>?) {

                        response?.body()?.nome

                        Toast.makeText(this@LoginActivity,response?.body()?.nome,Toast.LENGTH_LONG).show()
                        Log.i("FU",response?.body()?.nome)
                    }

                    override fun onFailure(call: Call<Profissional>?, t: Throwable?) {

                        Log.i("NAOFU",t.toString())
                        msgErro.text = t.toString()
                        Toast.makeText(this@LoginActivity,t.toString(),Toast.LENGTH_LONG).show()
                    }
                })


        return 1;
    }
}
