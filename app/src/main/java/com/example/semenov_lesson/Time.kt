package com.example.semenov_lesson

data class Time(
    var hour: Int,
    var minute: Int,
    var second: Int
){
    fun setTime(hour: Int, minutes: Int, seconds: Int){
        this.hour = hour
        this.minute = minutes
        this.second = seconds
    }
}