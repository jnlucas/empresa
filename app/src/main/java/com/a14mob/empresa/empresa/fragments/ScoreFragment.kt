package com.a14mob.empresa.empresa.fragments





import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.a14mob.empresa.empresa.R


/**
 * A simple [Fragment] subclass.
 */
class ScoreFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_score, container, false)
    }

}// Required empty public constructor
