package com.example.kotlinlesson.presentation.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentHomeBinding
import com.example.kotlinlesson.presentation.adapters.SliderButtonAdapter
import com.example.kotlinlesson.presentation.adapters.TrainingCoursesAdapter
import com.example.kotlinlesson.presentation.adapters.ViewPagerAdapter
import com.example.kotlinlesson.presentation.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var mBinding:FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java] //[] - get метод

        with(mBinding.trendingCourses){
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = TrainingCoursesAdapter(viewModel.coursesItems.value!!)
        }
        mBinding.crossfitSlider.adapter = viewModel.slider.value?.let { ViewPagerAdapter(it) }

        with(mBinding.buttonList){
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = viewModel.slider.value?.let { SliderButtonAdapter(it, viewModel.currentState) }
        }

        viewModel.coursesItems.observe(viewLifecycleOwner){
            mBinding.trendingCourses.adapter = TrainingCoursesAdapter(it)
        }

        viewModel.slider.observe(viewLifecycleOwner) {

            mBinding.crossfitSlider.adapter = ViewPagerAdapter(it)
            viewModel.currentState.value = mBinding.crossfitSlider.currentItem
        }

        viewModel.currentState.observe(viewLifecycleOwner) {
            println(it)
            mBinding
            mBinding.buttonList.adapter = SliderButtonAdapter(
                viewModel.slider.value!!,
                viewModel.currentState
            )
            mBinding.crossfitSlider.currentItem = it
        }

        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_menu)?.visibility = View.VISIBLE
    }

}