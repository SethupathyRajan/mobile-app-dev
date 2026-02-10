package com.example.nutritrak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.example.nutritrak.utils.DialogHelper
import com.example.nutritrak.utils.NotificationHelper

class ScanMealFragment : Fragment() {

    private lateinit var btnScan: MaterialButton
    private lateinit var progressBar: ProgressBar
    private lateinit var cameraIcon: ImageView
    private lateinit var scanResultText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotificationHelper.createNotificationChannel(requireContext())

        btnScan = view.findViewById(R.id.btn_scan)
        progressBar = view.findViewById(R.id.progress_scanning)
        cameraIcon = view.findViewById(R.id.iv_camera)
        scanResultText = view.findViewById(R.id.scan_result)

        btnScan.setOnClickListener { startScanning() }
    }

    private fun startScanning() {
        // Show progress bar
        progressBar.visibility = View.VISIBLE
        cameraIcon.visibility = View.GONE
        btnScan.isEnabled = false

        // Simulate scanning process
        view?.postDelayed({
            progressBar.visibility = View.GONE
            cameraIcon.visibility = View.VISIBLE
            btnScan.isEnabled = true

            // Show success dialog
            DialogHelper.showInfoDialog(
                requireContext(),
                "Scan Complete",
                "Chickpea Salad\n350 calories\nProtein: 12g | Carbs: 45g | Fat: 8g"
            ) {
                scanResultText.visibility = View.VISIBLE
                scanResultText.text = "✓ Chickpea Salad - 350 kcal"
                NotificationHelper.showMealSavedNotification(requireContext(), "Chickpea Salad")
            }
        }, 2000)
    }
}