package com.example.bestdeal.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Query
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.bestdeal.R
import com.example.bestdeal.ui.database.AppDatabase
import com.example.bestdeal.ui.database.Product
import kotlinx.android.synthetic.main.fragment_product_item.view.*

class ProductFragment : Fragment(){

    private lateinit var db : AppDatabase



    private lateinit var mView: View
    private lateinit var categoriesView: RecyclerView

    


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView=inflater.inflate(R.layout.fragment_product, container, false)

        db = AppDatabase.getInstance(context)
        categoriesView=mView.findViewById(R.id.recyclerView1)
        categoriesView.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriesView.adapter=ProductAdapter(dataProducts)



        return mView
    }

    class ProductAdapter(val productList: ArrayList<ProductViewModel>) :
        RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.fragment_product_item, parent, false)
            return ProductAdapter.ViewHolder(
                view,
                view.product_code,
                view.product_image,
                view.product_name,
                view.product_price,
                view.product_description,
                view.product_quantity

            )
        }

        override fun getItemCount(): Int = productList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val product=productList[position]

            val icon= ContextCompat.getDrawable(holder.icon.context,product.icono)
            holder.icon.setImageDrawable(icon)

            holder.name.text=product.nombre
            holder.code.text= product.codigo.toString()
            holder.descrption.text=product.descripcion
            holder.price.text=product.precio.toString()


        }

        class ViewHolder(
            val view: View,
            val code: TextView,
            val icon: ImageView,
            val name: TextView,
            val price: TextView,
            val descrption: TextView,
            val quantity: TextView
        ) :
            RecyclerView.ViewHolder(view) {

        }
    }}
