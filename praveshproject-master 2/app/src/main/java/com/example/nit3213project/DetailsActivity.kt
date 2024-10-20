package com.example.nit3213project

import android.os.Bundle
import android.view.MenuItem  // Add this import for MenuItem
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

        // Get the Entity passed from DashboardActivity
        val entity = intent.getParcelableExtra<Entity>("ENTITY")

        // Bind the entity's details to TextViews with titles
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val cultureTextView = findViewById<TextView>(R.id.cultureTextView)
        val domainTextView = findViewById<TextView>(R.id.domainTextView)
        val symbolTextView = findViewById<TextView>(R.id.symbolTextView)
        val parentageTextView = findViewById<TextView>(R.id.parentageTextView)
        val romanEquivalentTextView = findViewById<TextView>(R.id.romanEquivalentTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)

        // Set the text of each TextView with the title and value
        entity?.let {
            nameTextView.text = "Name: ${it.name}"
            cultureTextView.text = "Culture: ${it.culture}"
            domainTextView.text = "Domain: ${it.domain}"
            symbolTextView.text = "Symbol: ${it.symbol}"
            parentageTextView.text = "Parentage: ${it.parentage}"
            romanEquivalentTextView.text = "Roman Equivalent: ${it.romanEquivalent}"
            descriptionTextView.text = "Description: ${it.description}"
        }
    }

    // Handle the Up button (back navigation)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {  // MenuItem now resolved
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Close DetailsActivity and go back to DashboardActivity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
