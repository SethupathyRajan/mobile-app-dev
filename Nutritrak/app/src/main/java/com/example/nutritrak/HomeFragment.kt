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

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_meal -> {
                // Navigate to add meal screen
                findNavController().navigate(R.id.action_home_to_add_meal)
                true
            }
            R.id.action_scan_meal -> {
                // Navigate to scan meal screen
                findNavController().navigate(R.id.action_home_to_scan_meal)
                true
            }
            R.id.action_settings -> {
                // Navigate to settings
                findNavController().navigate(R.id.action_home_to_settings)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}