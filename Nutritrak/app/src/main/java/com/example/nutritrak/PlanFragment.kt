package com.example.nutritrak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.findNavController

class PlanFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plan, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plan_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_plan -> {
                // Navigate to create plan screen
                findNavController().navigate(R.id.action_plan_to_create_plan)
                true
            }
            R.id.action_edit_plan -> {
                // Navigate to edit plan screen
                findNavController().navigate(R.id.action_plan_to_edit_plan)
                true
            }
            R.id.action_settings -> {
                // Navigate to settings
                findNavController().navigate(R.id.action_plan_to_settings)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}