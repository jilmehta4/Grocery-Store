package com.example.project1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val details = intent.getStringExtra("details")
        val photo = intent.getStringExtra("image")
        val storRef: StorageReference? = photo?.let {
            FirebaseStorage.getInstance().getReferenceFromUrl(
                it
            )
        }
        val txtName : TextView? = findViewById(R.id.txtName);
        val txtPrice : TextView? = findViewById(R.id.txtPrice);
        val txtDetails : TextView? = findViewById(R.id.txtDetails);
        val image : ImageView? = findViewById(R.id.imgFull);
        val buy : Button? = findViewById(R.id.btnPurchase);


        txtName?.text = name
        txtPrice?.text = "$ " + price
        txtDetails?.text = details
        if (image != null) {
            Glide.with(this).load(storRef).circleCrop().into(image)
        };

        buy?.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java);
            startActivity(intent);
        }
    }
}