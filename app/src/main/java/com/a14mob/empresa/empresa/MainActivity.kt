package com.a14mob.empresa.empresa

import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.a14mob.empresa.empresa.fragments.AvaliacoesFragment
import com.a14mob.empresa.empresa.fragments.ScoreFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_score -> {

                this.changeFragment(ScoreFragment());
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_atividades -> {

                this.changeFragment(AvaliacoesFragment());

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sair -> {

                this@MainActivity.finish();

                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.changeFragment(ScoreFragment());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    /**
     * altera o fragment
     */
    fun changeFragment ( fragment: Fragment){
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit()
    }
}
