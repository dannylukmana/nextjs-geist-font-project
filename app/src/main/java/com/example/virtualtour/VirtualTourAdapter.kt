package com.example.virtualtour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.virtualtour.databinding.ItemVirtualTourBinding

class VirtualTourAdapter(
    private val tours: List<VirtualTour>,
    private val onItemClick: (VirtualTour) -> Unit
) : RecyclerView.Adapter<VirtualTourAdapter.VirtualTourViewHolder>() {

    inner class VirtualTourViewHolder(private val binding: ItemVirtualTourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(tour: VirtualTour) {
            with(binding) {
                titleText.text = tour.title
                distanceText.text = String.format("%.1f km", tour.distance)
                
                Glide.with(thumbnail.context)
                    .load(tour.thumbnailUrl)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(thumbnail)
                
                root.setOnClickListener { onItemClick(tour) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VirtualTourViewHolder {
        val binding = ItemVirtualTourBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VirtualTourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VirtualTourViewHolder, position: Int) {
        holder.bind(tours[position])
    }

    override fun getItemCount() = tours.size
}
