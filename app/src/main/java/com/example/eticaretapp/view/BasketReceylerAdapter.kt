package com.example.eticaretapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eticaretapp.R
import com.example.eticaretapp.model.Product
import kotlinx.android.synthetic.main.basket_recyler_row.view.*
import kotlinx.android.synthetic.main.recyler_row.view.*

class BasketReceylerAdapter(val basketList : List<Product>) : RecyclerView.Adapter<BasketReceylerAdapter.BasketViewHolder>() {
    class BasketViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.basket_recyler_row,parent,false)
    return BasketViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.itemView.BasketProductNameText.text=basketList.get(position).name
        holder.itemView.BasketPriceText.text="Fiyat: {basketList.get(position).price}"
        holder.itemView.BasketPriceText.text="adet : 0"
        Glide.with(holder.itemView.context).load(basketList.get(position).url).into(holder.itemView.basketImageView)



    }

    override fun getItemCount(): Int {
        return basketList.size
    }

}