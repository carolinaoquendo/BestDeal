package com.example.bestdeal.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestdeal.R
import kotlinx.android.synthetic.main.cardview_layout.view.*


class HomeFragment : Fragment(){
    private lateinit var mView: View
    private lateinit var categoriesView: RecyclerView

    private var dataCategories= arrayListOf(
        HomeViewModel(R.drawable.ic_phone, "Smartphones"),
        HomeViewModel(R.drawable.ic_laptop, "Portatiles"),
        HomeViewModel(R.drawable.ic_toys, "Juguetes"),
        HomeViewModel(R.drawable.ic_mochila, "Mochilas"),
        HomeViewModel(R.drawable.ic_tenis, "Tenis")

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView=inflater.inflate(R.layout.fragment_home, container, false)

        categoriesView=mView.findViewById(R.id.recyclerview)
        categoriesView.layoutManager=
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        categoriesView.adapter=HomeAdapter(dataCategories)

        return mView
    }

    class HomeAdapter(val homeList: ArrayList<HomeViewModel>) :
        RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.cardview_layout, parent, false)
            return HomeAdapter.ViewHolder(
                view,
                view.ImageView,
                view.titleHome
            )
        }

        override fun getItemCount(): Int = homeList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val home=homeList[position]

            val icon= ContextCompat.getDrawable(holder.icon.context,home.icono)
            holder.icon.setImageDrawable(icon)

            holder.name.text=home.nombre

        }

        class ViewHolder(
            val view: View,
            val icon: ImageView,
            val name: TextView
        ) :
            RecyclerView.ViewHolder(view) {

        }
    }
}