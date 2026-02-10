package com.example.nutritrak

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.textfield.TextInputEditText
import com.example.nutritrak.utils.DialogHelper
import com.example.nutritrak.utils.NotificationHelper
import java.util.Calendar

class CreatePlanFragment : Fragment() {

    private lateinit var btnSelectDate: MaterialButton
    private lateinit var btnGeneratePlan: MaterialButton
    private lateinit var toggleDuration: MaterialButtonToggleGroup
    private lateinit var progressBar: ProgressBar

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        NotificationHelper.createNotificationChannel(requireContext())

        btnSelectDate = view.findViewById(R.id.btn_select_start_date)
        btnGeneratePlan = view.findViewById(R.id.btn_generate_plan)
        toggleDuration = view.findViewById(R.id.toggle_duration)
        progressBar = view.findViewById(R.id.progress_generating)

        val calendar = Calendar.getInstance()
        selectedYear = calendar.get(Calendar.YEAR)
        selectedMonth = calendar.get(Calendar.MONTH)
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH)

        btnSelectDate.setOnClickListener { showDatePicker() }
        btnGeneratePlan.setOnClickListener { generatePlan() }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                selectedYear = year
                selectedMonth = month
                selectedDay = dayOfMonth
                updateDateButton()
            },
            selectedYear,
            selectedMonth,
            selectedDay
        )
        datePickerDialog.show()
    }

    private fun updateDateButton() {
        btnSelectDate.text = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
    }

    private fun generatePlan() {
        val planNameLayout = view?.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_plan_name)
        val planName = planNameLayout?.editText?.text.toString().trim()

        if (planName.isEmpty()) {
            DialogHelper.showInfoDialog(
                requireContext(),
                "Missing Information",
                "Please enter a plan name"
            )
            return
        }

        DialogHelper.showConfirmationDialog(
            requireContext(),
            "Generate Plan",
            "Create meal plan \"$planName\" starting from ${String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)}?",
            "Generate",
            "Cancel",
            onPositiveClick = {
                // Show progress
                progressBar.visibility = View.VISIBLE
                btnSelectDate.isEnabled = false
                btnGeneratePlan.isEnabled = false
                toggleDuration.isEnabled = false

                // Simulate plan generation
                view?.postDelayed({
                    progressBar.visibility = View.GONE
                    btnSelectDate.isEnabled = true
                    btnGeneratePlan.isEnabled = true
                    toggleDuration.isEnabled = true

                    NotificationHelper.showPlanCreatedNotification(requireContext(), planName)
                    DialogHelper.showInfoDialog(
                        requireContext(),
                        "Success",
                        "Plan \"$planName\" has been created successfully!"
                    ) {
                        requireActivity().onBackPressed()
                    }
                }, 2000)
            }
        )
    }
}