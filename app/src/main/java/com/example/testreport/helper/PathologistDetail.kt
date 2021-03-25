package com.example.testreport.helper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.testreport.R
import com.example.testreport.model.NewPatient

fun pathologistDetail(context: Context, canvas: Canvas){
    val textPaintPatient = TextPaint()
    val customTypeFace7 = ResourcesCompat.getFont(context, R.font.roboto_medium)
    textPaintPatient.typeface = customTypeFace7
    textPaintPatient.color = ContextCompat.getColor(context, R.color.custom_blue)
    //Pathologist Details
    textPaintPatient.letterSpacing = 0.001f
    textPaintPatient.isAntiAlias = true
    textPaintPatient.isLinearText = true
    textPaintPatient.isSubpixelText = true
    textPaintPatient.textSize = 6f
    textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
    canvas.drawText("Pathologist Signature", 170F, 330F, textPaintPatient)
    val customTypeFace8 = ResourcesCompat.getFont(context, R.font.roboto)
    textPaintPatient.typeface = customTypeFace8
    textPaintPatient.textSize = 4f
    canvas.drawText("Dr. Ganesh Hansram", 170F, 350F, textPaintPatient)
    canvas.drawText("Radiologist  |  MD", 170F, 355F, textPaintPatient)
    canvas.drawText("3542656", 190F, 360F, textPaintPatient)
    textPaintPatient.typeface = customTypeFace7
    canvas.drawText("Reg. No.", 170F, 360F, textPaintPatient)
}