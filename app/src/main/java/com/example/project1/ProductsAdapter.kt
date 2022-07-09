package com.example.project1

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class ProductsAdapter(options: FirebaseRecyclerOptions<Products>) : FirebaseRecyclerAdapter<Products, ProductsAdapter.MyViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Products) {

        val storRef: StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(model.image)
        Glide.with(holder.imgProduct.context).load(storRef).circleCrop().into(holder.imgProduct)

        holder.name.text = model.name
        holder.price.text = "$ " + model.price
        holder.itemView.setOnClickListener{
            Log.i("clicked","clicked");
            val i = Intent(it.context, DetailActivity::class.java)
            i.putExtra("name", model.name);
            i.putExtra("price", model.price);
            i.putExtra("image", model.image);
            i.putExtra("details", model.details);
            it.context.startActivity(i);
        }

    }

    inner class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.row_layout, parent, false))
    {

        val name: TextView = itemView.findViewById(R.id.productname)
        val price: TextView = itemView.findViewById(R.id.productprice)
        val imgProduct: ImageView = itemView.findViewById(R.id.productimg)
    }
}