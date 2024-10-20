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
        val cultureTextView: TextView = itemView.findViewById(R.id.property2TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entity, parent, false)
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder, position: Int) {
        val entity = entities[position]
        holder.nameTextView.text = entity.name  // Bind the name of the entity
        holder.cultureTextView.text = entity.culture  // Bind the culture of the entity

        // Handle item click event, trigger navigation to details page
        holder.itemView.setOnClickListener {
            onClick(entity)  // Pass the entity to the onClick function in DashboardActivity
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}
