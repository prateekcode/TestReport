package com.example.testreport.model

data class SampleType(
    val sampleTypeName: String, // test type in patient detail header
    val conditionList: List<Conditions>// Complete Blood count record list
)
