package com.a14mob.empresa.empresa.fragments





import android.annotation.SuppressLint
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
import android.util.Log
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


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {

        MapsInitializer.initialize(activity!!.applicationContext)
        mMap = googleMap!!
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.isMyLocationEnabled = true

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val prefs = inflater.context.getSharedPreferences("PROFISSIONAL", MODE_PRIVATE)
        var rootView = inflater?.inflate(R.layout.fragment_mapa, container, false)
        return rootView

    }

    override fun onStart() {
        super.onStart()
        mGoogleApiClient?.connect()
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

    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {

        val l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)

        if (mMap != null && l != null) {
            mMap.addMarker(MarkerOptions().position(LatLng(l.latitude, l.longitude)).title("abobrinha").snippet("sdsdsdsds"))

            var posicao = LatLng(l.latitude, l.longitude)
            val updateCam = CameraUpdateFactory.newLatLngZoom(posicao, 15f)
            mMap?.animateCamera(updateCam)
        }



    }

    override fun onConnectionSuspended(p0: Int) {
        Log.i("suspenso>>>>>","sdsdsdsdsds")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.i("falhou>>>>>","sdsdsdsdsds")
    }

}// Required empty public constructor
