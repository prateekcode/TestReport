package com.example.testreport.model

data class SampleType(
    val sampleTypeName: String, // test type in patient detail header
    val conditionHeader:String, // exp. Complete Blood Count
    val conditionList: List<Conditions>// Complete Blood count record list
)
