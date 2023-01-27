package com.example.kotlinlesson

import android.content.ContentValues.TAG
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlinlesson.databinding.ActivityMainBinding
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        println(currentFragment().tag + " Tag")
//        val observable = Observable.just(1, 2, 3)
//
//        val dispose = dataSource()
//           .subscribe({
//               Log.e(TAG, "Next Int $it")
//           }, {
//
//           },{
//
//           })

        val single = Single.just(1)

    }

    fun dataSource(): Observable<Int>{
        return Observable.create{
            subscriber ->
            for (i in 0..100){
                Thread.sleep(10000)
                subscriber.onNext(i)
            }
        }
    }


    fun currentFragment():Fragment{
        val navHostFragment: Fragment? =
            supportFragmentManager.findFragmentById(R.id.nav_host)
        navHostFragment?.childFragmentManager?.fragments?.get(0)

        return navHostFragment!!
    }

    fun visibilityBottomNav(res:Boolean){
        when(res){
            true-> mBinding.bottomMenu.visibility  = VISIBLE
            false-> mBinding.bottomMenu.visibility = GONE
        }
    }
}