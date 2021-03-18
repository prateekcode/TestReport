package com.example.testreport.model

data class Conditions(
    val condtionHeader:String,
    val conditionName:String,
    val conditionTypeList: List<Parameters>
)
