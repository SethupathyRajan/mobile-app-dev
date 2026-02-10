package com.example.nutritrak.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogHelper {

    fun showConfirmationDialog(
        context: Context,
        title: String,
        message: String,
        positiveText: String = "Save",
        negativeText: String = "Cancel",
        onPositiveClick: () -> Unit,
        onNegativeClick: () -> Unit = {}
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, _ ->
                onPositiveClick()
                dialog.dismiss()
            }
            .setNegativeButton(negativeText) { dialog, _ ->
                onNegativeClick()
                dialog.dismiss()
            }
            .show()
    }

    fun showInfoDialog(
        context: Context,
        title: String,
        message: String,
        buttonText: String = "OK",
        onDismiss: () -> Unit = {}
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(buttonText) { dialog, _ ->
                onDismiss()
                dialog.dismiss()
            }
            .show()
    }

    fun showProgressDialog(
        context: Context,
        title: String,
        message: String
    ): AlertDialog {
        val view = android.widget.FrameLayout(context).apply {
            layoutParams = android.widget.FrameLayout.LayoutParams(
                android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
            )
            addView(android.widget.ProgressBar(context).apply {
                layoutParams = android.widget.FrameLayout.LayoutParams(
                    android.widget.FrameLayout.LayoutParams.WRAP_CONTENT,
                    android.widget.FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = android.view.Gravity.CENTER
                }
            })
        }

        return MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setView(view)
            .setCancelable(false)
            .show()
    }

    fun showOptionsDialog(
        context: Context,
        title: String,
        items: Array<String>,
        onItemSelected: (Int) -> Unit
    ) {
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setItems(items) { dialog, which ->
                onItemSelected(which)
                dialog.dismiss()
            }
            .show()
    }
}