package com.a14mob.empresa.empresa.adapter

import android.content.Context

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.a14mob.empresa.empresa.R
import com.a14mob.empresa.empresa.entity.Score
import com.hendraanggrian.pikasso.into
import com.hendraanggrian.pikasso.picasso
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.score_item.view.*


class ScoreAdapter(private val scores: List<Score>, private val context: Context) : Adapter<ScoreAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val score = scores[position]

        holder?.let {
            it.profissional.text = score.profissional
            it.pontos.text = score.meta.toString()+"/"+score.pontos.toString()


        }


        picasso.load(score.foto.toString()).into(holder.itemView.foto)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.score_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {


        return scores.size
    }





    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val profissional = itemView.nome_prof
        val pontos = itemView.pontos
        val imagem = itemView.foto






        fun bindView(score: Score) {
            val profissional = itemView.nome_prof
            val pontos = itemView.pontos
            val imagem = itemView.foto

            pontos.text = score.profissional
            pontos.text = score.pontos.toString()


            picasso.load(score.foto.toString()).into(imagem)


        }

    }

}

