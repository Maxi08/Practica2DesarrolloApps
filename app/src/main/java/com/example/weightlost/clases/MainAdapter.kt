package com.example.weightlost.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weightlost.R
import kotlinx.android.synthetic.main.list_row_main.view.*

class MainAdapter(private val context: Context):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var dataList = emptyList<Pesos>()
    fun setListData(data:List<Pesos>){
        dataList=data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.list_row_main,parent,false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val peso =dataList[position]
        holder.bindView(peso)
    }
    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindView(peso:Pesos){

            itemView.text_viewfecha.text=peso.fecha.toString()
            itemView.text_viewpeso.text=peso.peso.toString()

        }
    }

}