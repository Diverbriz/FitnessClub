package com.example.kotlinlesson.presentation.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.databinding.ItemBinding
import com.example.kotlinlesson.domain.model.Slider
import com.makeramen.roundedimageview.RoundedImageView

class ViewPagerAdapter(private val slider: List<Slider>
//, private var currentState:Int
):
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.itemView.run {
        Log.e(TAG,"Ne error ${slider.size}")
        with(holder.binding.itemImage){
            setImageResource(slider[position].backgroundImg)
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

    }

    override fun getItemCount(): Int = slider.size

    inner class ViewHolder( val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

}


