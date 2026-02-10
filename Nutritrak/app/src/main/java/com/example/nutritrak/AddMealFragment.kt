package com.example.nutritrak

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.example.nutritrak.utils.DialogHelper
import com.example.nutritrak.utils.NotificationHelper
import java.util.Calendar

class AddMealFragment : Fragment() {

    private lateinit var btnDate: MaterialButton
    private lateinit var btnTime: MaterialButton
    private lateinit var btnSaveMeal: MaterialButton

    private var selectedYear = 0
    private var selectedMonth = 0
    private var selectedDay = 0
    private var selectedHour = 0
    private var selectedMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create notification channel
        NotificationHelper.createNotificationChannel(requireContext())

        btnDate = view.findViewById(R.id.btn_select_date)
        btnTime = view.findViewById(R.id.btn_select_time)
        btnSaveMeal = view.findViewById(R.id.btn_save_meal)

        // Get current date and time
        val calendar = Calendar.getInstance()
        selectedYear = calendar.get(Calendar.YEAR)
        selectedMonth = calendar.get(Calendar.MONTH)
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH)
        selectedHour = calendar.get(Calendar.HOUR_OF_DAY)
        selectedMinute = calendar.get(Calendar.MINUTE)

        btnDate.setOnClickListener { showDatePicker() }
        btnTime.setOnClickListener { showTimePicker() }
        btnSaveMeal.setOnClickListener { saveMeal() }
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

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                selectedHour = hourOfDay
                selectedMinute = minute
                updateTimeButton()
            },
            selectedHour,
            selectedMinute,
            true
        )
        timePickerDialog.show()
    }

    private fun updateDateButton() {
        btnDate.text = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
    }

    private fun updateTimeButton() {
        btnTime.text = String.format("%02d:%02d", selectedHour, selectedMinute)
    }

    private fun saveMeal() {
        val mealNameLayout = view?.findViewById<com.google.android.material.textfield.TextInputLayout>(R.id.til_meal_name)
        val mealName = mealNameLayout?.editText?.text.toString().trim()

        if (mealName.isEmpty()) {
            DialogHelper.showInfoDialog(
                requireContext(),
                "Missing Information",
                "Please enter a meal name"
            )
            return
        }

        DialogHelper.showConfirmationDialog(
            requireContext(),
            "Save Meal",
            "Add \"$mealName\" to your nutrition tracker?",
            "Save",
            "Cancel",
            onPositiveClick = {
                // Show progress dialog
                val progressDialog = DialogHelper.showProgressDialog(
                    requireContext(),
                    "Saving Meal",
                    "Adding $mealName to your records..."
                )

                // Simulate saving
                view?.postDelayed({
                    progressDialog.dismiss()
                    NotificationHelper.showMealSavedNotification(requireContext(), mealName)
                    DialogHelper.showInfoDialog(
                        requireContext(),
                        "Success",
                        "$mealName has been saved successfully!"
                    ) {
                        requireActivity().onBackPressed()
                    }
                }, 1500)
            }
        )
    }
}