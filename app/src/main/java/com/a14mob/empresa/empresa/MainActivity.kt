package com.a14mob.empresa.empresa

import android.content.Context
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import com.a14mob.empresa.empresa.fragments.AvaliacoesFragment
import com.a14mob.empresa.empresa.fragments.MapaFragment
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
            R.id.navigation_mapa -> {

                this.changeFragment(MapaFragment());

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


        val service = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val enable = service.isProviderEnabled(LocationManager.GPS_PROVIDER)

        PermissionUtils.validate(this@MainActivity,1,
                android.Manifest.permission.ACCESS_COARSE_LOCATION , android.Manifest.permission.ACCESS_FINE_LOCATION)

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
