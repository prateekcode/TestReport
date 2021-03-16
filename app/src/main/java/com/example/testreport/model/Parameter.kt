package com.example.testreport.model

data class Parameter(
    val hgb_haemoglobin: ParameterResponse,
    val hct_hematocrit: ParameterResponse,
    val hct_hgb: ParameterResponse,
    val mchc: ParameterResponse,
    val hct_hgb_calculated: ParameterResponse,
    val wbc_count: ParameterResponse,
    val neutrophil_percent: ParameterResponse,
    val lymphocyte_percent: ParameterResponse,
    val monocyte_percent: ParameterResponse,
    val eosinophil_percent: ParameterResponse,
    val basophil_percent: ParameterResponse,
    val neutrophil_absolute_number: ParameterResponse,
    val lymphocyte_absolute_number: ParameterResponse,
    val monocyte_absolute_number: ParameterResponse,
    val eosinophil_absolute_number: ParameterResponse,
    val basophil_absolute_number: ParameterResponse,
    val nlr_calculated: ParameterResponse,
)
