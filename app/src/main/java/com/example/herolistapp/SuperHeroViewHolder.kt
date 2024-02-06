package com.example.herolistapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.herolistapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder (view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: SuperheroItemResponse, onItemSelected: (String) -> Unit){
        binding.tvsuperheroname.text = superheroItemResponse.name
        binding.ivSuperhero
        Picasso.get().load(superheroItemResponse.superheroimage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superheroItemResponse.superheroId) }
    }
}