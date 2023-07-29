package com.ichwan.gigihmodule.openstreetmaps

import com.ichwan.gigihmodule.R
import com.ichwan.gigihmodule.databinding.LayoutTooltipBinding
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class CustomInfoWIndow(mapView: MapView?) : InfoWindow(R.layout.layout_tooltip, mapView) {

    lateinit var binding: LayoutTooltipBinding

    override fun onOpen(item: Any) {
        binding = LayoutTooltipBinding.bind(view)

        //show marker in maps
        val marker = item as Marker
        val infoWindowData = marker.relatedObject as ModelMain

        val namaLokasi = binding.tvNamaLokasi
        val alamat = binding.tvAlamat
        val imgClose = binding.imageClose

        namaLokasi.text = infoWindowData.strName
        alamat.text = infoWindowData.strVicinity
        imgClose.setOnClickListener {
            marker.closeInfoWindow()
        }
    }

    override fun onClose() {

    }

}