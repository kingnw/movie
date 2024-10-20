package com.example.nit3213project

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Set up the toolbar for back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the Up button for back navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get the Entity passed from another activity
        val entity = intent.getParcelableExtra<Entity>("ENTITY")

        // Bind the entity's details to TextViews
        val imageView = findViewById<ImageView>(R.id.entityImageView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val locationTextView = findViewById<TextView>(R.id.locationTextView)
        val yearTextView = findViewById<TextView>(R.id.yearTextView)
        val architectTextView = findViewById<TextView>(R.id.architectTextView)
        val styleTextView = findViewById<TextView>(R.id.styleTextView)
        val heightTextView = findViewById<TextView>(R.id.heightTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        // Set the text of each TextView with entity details
        entity?.let {
            nameTextView.text = it.name
            locationTextView.text = it.location
            yearTextView.text = it.yearCompleted.toString()
            architectTextView.text = it.architect
            styleTextView.text = it.style
            heightTextView.text = it.height.toString()
            descriptionTextView.text = it.description

            imageView.setImageResource(it.imageResource)

        }
    }

    // Handle the Up button (back navigation)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Close DetailsActivity and go back to the previous activity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}