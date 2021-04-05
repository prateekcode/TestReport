package com.example.testreport.model

data class Conditions(
    val condtionHeader:String, // exp. Complete Blood Count
    val conditionName:String,  // exp Anaemia
    val testParameters: List<Parameters> // Test record
)
