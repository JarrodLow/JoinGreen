package com.example.joingreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RewardViewModel : ViewModel() {

    var totalRewardPoints: Int = 0


    init {
        Log.d("ViewModel", "ViewModel created")
    }

    fun updatePoints(points:Int){
        this.totalRewardPoints=points
    }

    fun usePoints(points:Int){
        totalRewardPoints= totalRewardPoints - points
    }



    override fun onCleared() {
        Log.d("ViewModel", "ViewModel destroyed")
        super.onCleared()
    }
}