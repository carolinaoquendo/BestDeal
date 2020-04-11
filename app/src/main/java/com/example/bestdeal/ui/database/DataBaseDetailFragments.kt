package com.example.bestdeal.ui.database

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bestdeal.R
import kotlinx.android.synthetic.main.fragment_product_item.view.*

class DataBaseDetailFragments : Fragment() {

    private lateinit var mView: View
    private lateinit var db: AppDatabase
    private lateinit var mAction: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(R.layout.fragment_product_item, container, false)

        mView.save_button.setOnClickListener { saveProduct() }

        db = AppDatabase.getInstance(context)
        arguments?.apply {
            mAction = getString("action") ?: ""

            if (mAction == "SHOW_PRODUCT") {
                loadProduct(getInt("product_code"))
            }
        }

        return mView
    }

    private fun loadProduct(productCode: Int) {
        val product = db.productDAO().findByCode(productCode)

        mView.product_code.setText("${product.code}")
        mView.product_name.setText(product.name)
        mView.product_description.setText(product.description)
        mView.product_quantity.setText("${product.quantity}")
        mView.product_price.setText("${product.price}")
    }

    private fun saveProduct() {
        val product = Product(
            mView.product_code.text.toString().toInt(),
            mView.product_name.text.toString(),
            mView.product_description.text.toString(),
            mView.product_quantity.text.toString().toInt(),
            mView.product_price.text.toString().toDouble()
        )

        when (mAction) {
            "ADD_PRODUCT" -> db.productDAO().insertAll(product)
            "SHOW_PRODUCT" -> db.productDAO().update(product)
        }

        findNavController().navigateUp()

        Toast.makeText(context, "Saving Product", Toast.LENGTH_SHORT).show()
    }



}