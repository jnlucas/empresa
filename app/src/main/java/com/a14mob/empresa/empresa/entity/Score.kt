package com.a14mob.empresa.empresa.entity

import java.util.*

data class Score(var pontos: Float, var profissional: String,var vencimento: String,
                 var equipe: String, var avaliacoes: List<Avaliacao>, var meta:String, var foto: String)
