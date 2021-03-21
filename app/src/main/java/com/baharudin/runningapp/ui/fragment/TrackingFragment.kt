package com.baharudin.runningapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baharudin.runningapp.R
import com.baharudin.runningapp.databinding.FragmentTrackingBinding
import com.baharudin.runningapp.other.Constant.ACTION_PAUSE
import com.baharudin.runningapp.other.Constant.ACTION_START_OR_RESUME
import com.baharudin.runningapp.other.Constant.MAPS_ZOOM
import com.baharudin.runningapp.other.Constant.POLYLINE_COLOR
import com.baharudin.runningapp.other.Constant.POLYLINE_WIDTH
import com.baharudin.runningapp.service.Polyline
import com.baharudin.runningapp.service.TrackingService
import com.baharudin.runningapp.ui.viewModel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    private var _binding : FragmentTrackingBinding? = null
    private val binding get() = _binding!!
    private val viewModel : MainViewModel by viewModels()
    private var map : GoogleMap? = null

    private var isTracking = false
    private var pathPoint = mutableListOf<Polyline>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentTrackingBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.onCreate(savedInstanceState)

        binding.btnToggleRun.setOnClickListener {
            toggleRun()
        }

        binding.mapView.getMapAsync{
            map = it
            addAllPolyline()
        }
        subcribeToObserve()
    }

    private fun subcribeToObserve() {
        TrackingService.isTracking.observe(viewLifecycleOwner, {
            updateTracking(it)
        })
        TrackingService.pathPoints.observe(viewLifecycleOwner, {
            pathPoint = it
            addLatestPolyline()
            moveCameraToUser()
        })
    }
    private fun toggleRun () {
        if (isTracking){
            sendCommandService(ACTION_PAUSE)
        }else {
            sendCommandService(ACTION_START_OR_RESUME)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTracking (isTracking : Boolean) {
        if (!isTracking){
            binding.btnToggleRun.text = "Start"
            binding.btnFinishRun.visibility = View.VISIBLE
        }else{
            binding.btnToggleRun.text = "Stop"
            binding.btnFinishRun.visibility =View.VISIBLE
        }
    }

    private fun moveCameraToUser() {
        if (pathPoint.isNotEmpty() && pathPoint.last().isNotEmpty()) {
            map?.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            pathPoint.last().last(),
                            MAPS_ZOOM
                    )
            )
        }
    }

    private fun addAllPolyline(){
        for (polyline in pathPoint){
            val polylineOptions = PolylineOptions()
                    .color(POLYLINE_COLOR)
                    .width(POLYLINE_WIDTH)
                    .addAll(polyline)
            map?.addPolyline(polylineOptions)

        }
    }

    private fun addLatestPolyline(){
        if(pathPoint.isNotEmpty() && pathPoint.last().size > 1){
            val preLatLng = pathPoint.last()[pathPoint.last().size - 2]
            val lastPolyline = pathPoint.last().last()
            val polylineOptions = PolylineOptions()
                    .color(POLYLINE_COLOR)
                    .width(POLYLINE_WIDTH)
                    .add(preLatLng)
                    .add(lastPolyline)
            map?.addPolyline(polylineOptions)
        }
    }

    private fun sendCommandService(action : String) =
            Intent(requireContext(),TrackingService::class.java).also {
                it.action = action
                requireContext().startService(it)
            }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        binding.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView?.onSaveInstanceState(outState)
    }

}