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

import android.content.Context.MODE_PRIVATE
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_mapa.*
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*

/**
 * A simple [Fragment] subclass.
 */
class MapaFragment : Fragment() , OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {



    private lateinit var mMap: GoogleMap
    var mGoogleApiClient: GoogleApiClient? = null

    override fun onMapReady(googleMap: GoogleMap?) {

        MapsInitializer.initialize(activity!!.applicationContext)
        mMap = googleMap!!
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        val prefs = inflater.context.getSharedPreferences("PROFISSIONAL", MODE_PRIVATE)


        var rootView = inflater?.inflate(R.layout.fragment_mapa, container, false)



        return rootView



    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MapaFragment)

        mGoogleApiClient = GoogleApiClient.Builder(activity!!.applicationContext)
                .addConnectionCallbacks(this@MapaFragment)
                .addOnConnectionFailedListener(this@MapaFragment)
                .addApi(LocationServices.API)
                .build()

    }

    override fun onConnected(p0: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}// Required empty public constructor
