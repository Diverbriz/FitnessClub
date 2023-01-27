package com.example.kotlinlesson.presentation.async

import android.os.AsyncTask
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

interface LoadingImplementation {
    fun onFinishedLoading()
}

class LoaderAsync(private val listener: LoadingImplementation) : AsyncTask<Boolean, Void, Void>() {
    override fun doInBackground(vararg p0: Boolean?): Void? {
        for (i in 0 until 10) {
            Thread.sleep(1000)
        }
        TODO("Not yet implemented")
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        listener.onFinishedLoading()
    }


}