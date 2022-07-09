package com.example.project1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase


class ProductActivity : AppCompatActivity() {

    private var rView: RecyclerView? = null;
    private var adapter: ProductsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product)

        val query = FirebaseDatabase.getInstance().reference.child("products")
        val options = FirebaseRecyclerOptions.Builder<Products>().setQuery(query, Products::class.java).build()
        adapter = ProductsAdapter(options)

        rView = findViewById(R.id.rView)
        rView?.layoutManager = LinearLayoutManager(this)
        rView?.adapter = adapter;
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }
}