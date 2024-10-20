package com.example.nit3213project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class EntityAdapter(private val entities: List<Entity>, private val onClick: (Entity) -> Unit) :
    RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.property1TextView)
        val locationTextView: TextView = itemView.findViewById(R.id.property2TextView)
        val yearTextView: TextView = itemView.findViewById(R.id.property3TextView)
        // Add more views for architect, style, height, description, etc.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.nameTextView.text = entity.name  // Bind name
        holder.locationTextView.text = entity.location  // Bind location
        holder.yearTextView.text = entity.yearCompleted.toString()  // Bind year

        // Handle click event
        holder.itemView.setOnClickListener {
            onClick(entity)  // Navigate to DetailsActivity on click
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
