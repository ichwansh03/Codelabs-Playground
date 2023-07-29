package com.ichwan.gigihmodule.openstreetmaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.ActivityMapsBinding
import org.json.JSONException
import org.json.JSONObject
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapController
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
import java.io.IOException
import java.nio.charset.StandardCharsets

class MapsActivity : AppCompatActivity() {

    private var mainList: MutableList<ModelMain> = ArrayList()
    lateinit var binding: ActivityMapsBinding
    lateinit var mapController: MapController
    lateinit var overlayItem: ArrayList<OverlayItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val geoPoint = GeoPoint(-6.3035467, 106.8693513)
        binding.apply {
            mapView.setMultiTouchControls(true)
            mapView.controller.animateTo(geoPoint)
            mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
            mapView.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

            mapController = mapView.controller as MapController
            mapController.setCenter(geoPoint)
            mapController.zoomTo(15)
        }

        geoLocationMarker()
    }

    private fun geoLocationMarker() {
        try {
            val stream = assets.open("sample_maps.json")
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()

            val strContent = String(buffer, StandardCharsets.UTF_8)
            try {
                val jsonObject = JSONObject(strContent)
                val jsonArrResult = jsonObject.getJSONArray("results")
                for (i in 0 until jsonArrResult.length()){
                    val jsonObjectResult = jsonArrResult.getJSONObject(i)
                    val modelMain = ModelMain()
                    modelMain.strName = jsonObjectResult.getString("name")
                    modelMain.strVicinity = jsonObjectResult.getString("vicinity")

                    val jsonObjectGeo = jsonObjectResult.getJSONObject("geometry")
                    val jsonObjectLoc = jsonObjectGeo.getJSONObject("location")
                    modelMain.latitude = jsonObjectLoc.getDouble("lat")
                    modelMain.longitude = jsonObjectLoc.getDouble("lng")
                    mainList.add(modelMain)
                }
                initMarker(mainList)
            } catch (e: JSONException){
                e.printStackTrace()
            }
        } catch (ignored: IOException){
            Toast.makeText(this, "Any wrong in your connection!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initMarker(modelList: List<ModelMain>) {
        for (i in modelList.indices){
            overlayItem = ArrayList()
            overlayItem.add(
                OverlayItem(
                    modelList[i].strName, modelList[i].strVicinity, GeoPoint(modelList[i].latitude, modelList[i].longitude)
                )
            )

            val info = ModelMain()
            info.strName = modelList[i].strName
            info.strVicinity = modelList[i].strVicinity

            val marker = Marker(binding.mapView)
            marker.apply {
                icon = resources.getDrawable(R.drawable.ic_location)
                position = GeoPoint(modelList[i].latitude, modelList[i].longitude)
                relatedObject = info
                infoWindow = CustomInfoWIndow(mapView = binding.mapView)
                setOnMarkerClickListener { marker, _ ->
                    marker.showInfoWindow()
                    true
                }

                binding.mapView.overlays.add(marker)
                binding.mapView.invalidate()
            }
        }
    }

}