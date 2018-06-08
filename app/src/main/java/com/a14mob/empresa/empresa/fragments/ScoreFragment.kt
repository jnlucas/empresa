package com.a14mob.empresa.empresa.fragments





import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.a14mob.empresa.empresa.R
import com.a14mob.empresa.empresa.entity.Profissional
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.widget.TextView
import com.a14mob.empresa.empresa.PermissionUtils
import com.a14mob.empresa.empresa.adapter.ScoreAdapter
import com.a14mob.empresa.empresa.entity.Avaliacao
import com.a14mob.empresa.empresa.entity.Score
import com.a14mob.empresa.empresa.retrofit.RetroFitRestAPI
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.GsonBuilder
import com.orhanobut.hawk.Hawk
import com.orhanobut.hawk.Hawk.get
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_score.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {



    var nome: String = ""

    var cpf: String = ""

    var profissionalId: Int = 0

    var meta: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val prefs = inflater.context.getSharedPreferences("PROFISSIONAL", MODE_PRIVATE)

        profissionalId = prefs.getString("profissionalId", null).toInt()

        meta = prefs.getString("meta", null).toInt()

        nome = prefs.getString("nome", null).toString()

        cpf = prefs.getString("cpf", null).toString()


        var rootView = inflater?.inflate(R.layout.fragment_score, container, false)



        return rootView



    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        metaPontos.text = "Ranking da equipe "

        score(profissionalId.toInt(), meta.toInt())

        PermissionUtils.atualizaToken(cpf,FirebaseInstanceId.getInstance().token.toString())

    }



    fun carregarInformacoes(scores: List<Score>){

        val recyclerView = score_list_recyclerview
            recyclerView.adapter = ScoreAdapter(scores, this@ScoreFragment.context!!)

            val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            recyclerView.layoutManager = layoutManager


    }

    fun score(profissionalId: Int, meta:Int){

        PermissionUtils.api.scoreProfissional(profissionalId,meta)
                .enqueue(object : Callback<List<Score>> {
                    override fun onResponse(call: Call<List<Score>>?, response: Response<List<Score>>?) {

                        this@ScoreFragment.carregarInformacoes(response?.body() as List<Score>)

                    }

                    override fun onFailure(call: Call<List<Score>>?, t: Throwable?) {


                    }
                })

    }

}// Required empty public constructor
