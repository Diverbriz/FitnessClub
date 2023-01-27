package com.example.kotlinlesson.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.kotlinlesson.databinding.RecyclerviewItemBinding
import com.example.kotlinlesson.domain.model.Course

class TrainingCoursesAdapter(private var list: List<Course>): RecyclerView.Adapter<CoursesHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesHolder
        = CoursesHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CoursesHolder, position: Int) {
        with(holder.binding){
            cardImageView.setImageResource(list[position].url.toInt())
            cardTitle.text = list[position].title
            cardDescription.text = list[position].description.substring(0,
                list[position].description.length.coerceAtMost(20)
            )
        }

        holder.binding.itemCard.setOnClickListener{

        }
    }

    override fun getItemCount(): Int = list.size

}

class CoursesHolder(val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root)