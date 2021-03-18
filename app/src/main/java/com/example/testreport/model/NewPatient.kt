package com.example.testreport.model

data class NewPatient(
    val patientName: String,
    val age: Int,
    val gender: String,
    val referredDoctor: String,
    val patientId: String,
    val authorizationDate: String,
    val reportDate: String,
    val sampleType: SampleType
)
