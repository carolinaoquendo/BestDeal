package com.example.bestdeal.ui.database

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.bestdeal.OnItemClickListener
import com.example.bestdeal.R
import kotlinx.android.synthetic.main.fragment_cart_view.view.*
import kotlinx.android.synthetic.main.fragment_inventario.view.*
import kotlinx.android.synthetic.main.fragment_product_item.view.*


class DataBaseListFragment : Fragment(), OnItemClickListener {

    companion object {
        private const val TAG = "DataBaseListFragment"
    }

    private lateinit var mView: View
    private lateinit var mProductAdapter: ProductAdapter
    private lateinit var db:AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        mView = inflater.inflate(R.layout.fragment_inventario, container, false)


        db= AppDatabase.getInstance(context)

        loadProducts()

        return mView
    }

    private fun loadProducts() {
        val productsList = db.productDAO().getAll()

        mProductAdapter = ProductAdapter()
        mProductAdapter.data = productsList
        mProductAdapter.itemClickListener = this

        mView.recyclerView.adapter = mProductAdapter
        mView.recyclerView.layoutManager = LinearLayoutManager(context)


        Log.i(TAG, "Total Products: ${productsList.size}")
    }

    private fun addProduct() {
        val bundle = bundleOf(
            "action" to "ADD_PRODUCT"
        )
        findNavController().navigate(R.id.nav_database_details,bundle)
    }

    override fun onItemClick(view: View, position: Int) {
        val bundle = bundleOf(
            "action" to "SHOW_PRODUCT",
            "product_code" to view.product_code.text.toString().toInt()
        )

        findNavController().navigate(R.id.nav_database_details, bundle)
    }




    private class ProductAdapter() :
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        var data: List<Product> = emptyList()
        var itemClickListener: OnItemClickListener?=null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.fragment_cart_view, parent, false)

            return ProductViewHolder(
                itemView,
                itemView.product_code_text,
                itemView.product_name_text
            )
        }

        override fun getItemCount(): Int = data.size

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = data[position]
            holder.codeTxt.text = "${product.code}"
            holder.nameTxt.text = product.name
            holder.view.setOnClickListener {
                itemClickListener?.onItemClick(it, holder.adapterPosition)
            }
        }

        class ProductViewHolder(val view: View, val codeTxt: TextView, val nameTxt: TextView) :
            RecyclerView.ViewHolder(view)
    }
}