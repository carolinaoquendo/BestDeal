package com.example.bestdeal.ui.inventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bestdeal.R
import com.example.bestdeal.ui.inve.InventoryViewModel

class InventoryFragment : Fragment() {

    private lateinit var inventoryViewModel: InventoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inventoryViewModel =
            ViewModelProviders.of(this).get(InventoryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_inventario, container, false)

        return root
    }
}