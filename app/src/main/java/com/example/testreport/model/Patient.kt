package com.example.testreport.model

data class Patient(
    val patient_name: String,
    val patient_age: Int,
    val patient_gender: String,
    val referred_doctor: String,
    val sample_type: String,
    val patient_id: Int,
    val parameter: Parameter,
    //val parameter: List<ParameterResponse>
)
