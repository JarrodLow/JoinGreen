package com.example.joingreen.Ui.Reward
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class RewardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rewardz Fragment"
    }
    val text: LiveData<String> = _text
}