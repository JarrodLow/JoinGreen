package com.example.joingreen.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RewardHomeViewModel : ViewModel() {

    val rewardPoints =MutableLiveData<Int>()

    fun setrewardPoints(points:Int){
        rewardPoints.setValue(points)
    }
}