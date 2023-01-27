package com.example.kotlinlesson.presentation.fragments

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.kotlinlesson.R
import com.example.kotlinlesson.databinding.FragmentGetStartedBinding
import com.example.kotlinlesson.presentation.viewmodel.GetStartedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class GetStartedFragment : Fragment() {

    companion object {
        fun newInstance() = GetStartedFragment()
    }

    private lateinit var binding: FragmentGetStartedBinding

    private lateinit var actBinding:BottomNavigationView
    private lateinit var viewModel: GetStartedViewModel
    private var progressStatus = 0
    private var handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)

        binding.login.setOnClickListener{
            progressStatus = 0
            Thread {

                while (progressStatus < 100){
                    progressStatus++

                    Handler(Looper.getMainLooper()).postDelayed({
                           binding.progressBar.progress = progressStatus
                    },3)
                }
            }.start()
        }
        val observable =Observable.just(1,2,3)
        val dispose = dataSource()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
              Log.e(TAG, "Next $it")
                binding.progressBar.progress = if(it<100) it else 100
            },{
                it.localizedMessage?.let { it1 -> Log.e(TAG, it1) }
            },{

            })
//        mBinding = activity?.let { ActivityMainBinding.inflate(it.layoutInflater) }!!
        binding.getStartedBtn.setOnClickListener{
            binding.progressBar.visibility = View.VISIBLE

//            Thread(Runnable {
//                while (progressStatus < 100){
//                    progressStatus+=1
//                    Thread.sleep(5)
//
//                    handler.post {
//                        binding.progressBar.progress = progressStatus
//                    }
//                }
//
//                handler.post{
//                    view?.findNavController()?.navigate(R.id.action_getStartedFragment_to_homeFragment)
//                }
//
//            }).start()

        }


        return binding.root
    }

    fun dataSource():Flowable<Int>{
        return Flowable.create({
            subscribe ->
            for (i in 0..1000000){

                subscribe.onNext(i)
            }
            subscribe.onComplete()
        }, BackpressureStrategy.LATEST)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GetStartedViewModel::class.java)

        activity?.findViewById<BottomNavigationView>(R.id.bottom_menu)?.visibility = View.GONE
    }

}