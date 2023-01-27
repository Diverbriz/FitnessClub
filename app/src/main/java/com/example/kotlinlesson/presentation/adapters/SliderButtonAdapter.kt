package com.example.kotlinlesson.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.SliderNextBinding
import com.example.kotlinlesson.domain.model.Slider

class SliderButtonAdapter(private var backgroundImg: List<Slider>, private var currentState:MutableLiveData<Int>) : RecyclerView.Adapter<SliderButtonAdapter.NextButtonVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextButtonVH =
        NextButtonVH(SliderNextBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NextButtonVH, position: Int) {

        with(holder.mBinding.button){

            if(position == currentState.value){
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_pressed))
            }
            else{
                setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_disabled))
            }

            setOnClickListener{
                currentState.value = position
                println(currentState.value)
            }

        }

    }

    override fun getItemCount(): Int = backgroundImg.size

    inner class NextButtonVH(val mBinding:SliderNextBinding): RecyclerView.ViewHolder(mBinding.root)
}


