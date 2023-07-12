package com.example.koltinflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow {
        val startingValue = 10
        emit(startingValue)
        var currentValue = startingValue
        while (currentValue > 0){
            delay(1000L)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        getCountDownFlow()
    }

    private fun getCountDownFlow(){
        viewModelScope.launch {

            countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .collect{ timer ->
                println("This countDown is $timer")
            }
        }
    }


}