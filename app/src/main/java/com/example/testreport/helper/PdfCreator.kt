package com.example.testreport.helper

import android.content.Context
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.text.TextPaint
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.testreport.R
import com.google.android.material.color.MaterialColors.getColor
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object PdfCreator {

    @RequiresApi(Build.VERSION_CODES.M)
    fun createTestReportPdf(
        context: Context,
        bitmap: Bitmap,
        patientName: String,
        patientAge: Int,
        patientGender: String,
        referredDoctor: String,
        sampleType:String,
        patientId:Int,
        authorizationDate: String,
        reportDate: String,
        hgb_haemoglobin_result:String,
        hgb_haemoglobin_units:String,
        hgb_haemoglobin_ref_range:String,
        hct_hematocrit_result:String,
        hct_hematocrit_unit:String,
        hct_hematocrit_ref_range:String,
        hct_hgb_result:String,
        hct_hgb_units:String,
        hct_hgb_ref_range:String,
        mchc_result:String,
        mchc_units:String,
        mchc_ref_range:String,
        wbc_results:String,
        wbc_units:String,
        wbc_ref_range:String,
        neutrophil_percent_results:String,
        neutrophil_percent_units:String,
        neutrophil_percent_ref_range:String,
        lymphocyte_percent_results:String,
        lymphocyte_percent_units:String,
        lymphocyte_percent_ref_range:String,
        monocyte_percent_results:String,
        monocyte_percent_units:String,
        monocyte_percent_ref_range:String,
        eosinophil_percent_results:String,
        eosinophil_percent_units:String,
        eosinophil_percent_ref_range:String,
        basophil_percent_results:String,
        basophil_percent_units:String,
        basophil_percent_ref_range:String,
        neutrophil_absolute_num_results:String,
        neutrophil_absolute_num_units:String,
        neutrophil_absolute_num_ref_range:String,
        lymphocyte_absolute_num_results:String,
        lymphocyte_absolute_num_units:String,
        lymphocyte_absolute_num_ref_range:String,
        monocyte_absolute_num_results:String,
        monocyte_absolute_num_units:String,
        monocyte_absolute_num_ref_range:String,
        eosinophil_absolute_num_results:String,
        eosinophil_absolute_num_units:String,
        eosinophil_absolute_num_ref_range:String,
        basophil_absolute_num_results:String,
        basophil_absolute_num_units:String,
        basophil_absolute_num_ref_range:String,
        nlr_calculated_results: String,
        nlr_calculated_units:String,
        nlr_calculated_ref_range:String
    ) {
        val pdfDocument = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            PdfDocument()
        } else {
            TODO("VERSION.SDK_INT < KITKAT")
        }

        //Start of Page 1

        val myPageInfo = PdfDocument.PageInfo.Builder(250, 400, 1).create()
        val myPage1 = pdfDocument.startPage(myPageInfo) as PdfDocument.Page

        val paint = Paint()
        val canvas = myPage1.canvas as Canvas


        //Start of Top Header
        paint.color = ContextCompat.getColor(context, R.color.custom_blue)
        val customTypeFace = ResourcesCompat.getFont(context, R.font.roboto_black)
        paint.typeface = customTypeFace
        paint.textSize = 14F
        canvas.drawText("Health Choice Clinic", 80F, 25F, paint)

        val customTypeFace2 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        paint.typeface = customTypeFace2
        paint.letterSpacing = 0.001f
        paint.isAntiAlias = true
        paint.isLinearText = true
        paint.isSubpixelText = true
        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.textSize = 5F
        canvas.drawText("8th floor Suratwala Mark Plazzo, Hinjawadi Pune", 80F, 35F, paint)


        canvas.drawLine(194F, 30F, 194F, 42F, paint)
        paint.typeface = customTypeFace2
        paint.textSize = 5F
        canvas.drawText("9452451241", 198F, 35F, paint)
        canvas.drawText("8245121416", 198F, 42F, paint)

        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.textSize = 5F
        canvas.drawText("Maharastra, 41007", 80F, 40F, paint)

        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true
        canvas.drawBitmap(
            BITMAP_RESIZER(bitmap, 50, 20)!!,
            10f,
            20f,
            Paint(Paint.FILTER_BITMAP_FLAG)
        )
        //End of Top Header

        //Start of Patient Detail Section
        paint.color = ContextCompat.getColor(context, R.color.custom_blue)
        canvas.drawRect(0F, 50F, 300F, 110F, paint)
        val textPaintPatient = TextPaint()
        val customTypeFace4 = ResourcesCompat.getFont(context, R.font.roboto_bold)
        textPaintPatient.typeface = customTypeFace4
        textPaintPatient.letterSpacing = 0.001f
        textPaintPatient.isAntiAlias = true
        textPaintPatient.isLinearText = true
        textPaintPatient.isSubpixelText = true
        textPaintPatient.color = ContextCompat.getColor(context, R.color.white)
        textPaintPatient.textSize = 6F
        canvas.drawText("Patient Name   :", 10F, 65F, textPaintPatient)
        canvas.drawText("Age & Gender   :", 10F, 75F, textPaintPatient)
        canvas.drawText("Referred by      :", 10F, 85F, textPaintPatient)
        canvas.drawText("Sample Type    :", 10F, 95F, textPaintPatient)

        val customTypeFace5 = ResourcesCompat.getFont(context, R.font.roboto)
        textPaintPatient.typeface = customTypeFace5
        canvas.drawText(patientName, 60F, 65F, textPaintPatient)
        canvas.drawText("$patientAge Year/${patientGender}", 60F, 75F, textPaintPatient)
        canvas.drawText(referredDoctor, 60F, 85F, textPaintPatient)
        canvas.drawText(sampleType, 60F, 95F, textPaintPatient)

        paint.color = ContextCompat.getColor(context, R.color.white)
        val midX = (canvas.width / 2).toFloat()
        canvas.drawLine(midX, 50F, midX, 110F, paint)

        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
        canvas.drawText("Authorization Date   :", midX + 4F, 65F, textPaintPatient)
        canvas.drawText("Report Date   :", midX + 4F, 75F, textPaintPatient)
        canvas.drawText("Patient ID      :", midX + 4F, 85F, textPaintPatient)

        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(authorizationDate, midX + 62, 65F, textPaintPatient)
        canvas.drawText(reportDate, midX + 62, 75F, textPaintPatient)
        canvas.drawText(patientId.toString(), midX + 62, 85F, textPaintPatient)
        //End of the Patient Detail


        //Table of Test
        paint.color = ContextCompat.getColor(context, R.color.custom_light_blue)
        canvas.drawRect(10F, 115F, 80F, 125F, paint)
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
        canvas.drawText("Parameter", 20F, 122F, textPaintPatient)

        canvas.drawRect(83F, 115F, 123F, 125F, paint)
        canvas.drawText("Results", 88F, 122F, textPaintPatient)

        canvas.drawRect(126F, 115F, 162F, 125F, paint)
        canvas.drawText("Units", 130F, 122F, textPaintPatient)

        canvas.drawRect(165F, 115F, 202F, 125F, paint)
        canvas.drawText("Ref. Range", 168F, 122F, textPaintPatient)

        canvas.drawRect(205F, 115F, 237F, 125F, paint)
        canvas.drawText("Barcode", 209F, 122F, textPaintPatient)

        paint.color = ContextCompat.getColor(context, R.color.custom_extra_light_blue)
        canvas.drawRect(10F, 125F, 237F, 135F, paint)

        textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
        canvas.drawText("Complete Blood Count", 95F, 132F, textPaintPatient)

        paint.color = ContextCompat.getColor(context, R.color.grey)
        canvas.drawRect(10F, 135F, 237F, 145F, paint)
        val customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        textPaintPatient.typeface = customTypeFace3
        textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
        canvas.drawText("Anaemia", 115F, 142F, textPaintPatient)

        //Parameter Content
        textPaintPatient.letterSpacing = 0.001f
        textPaintPatient.isAntiAlias = true
        textPaintPatient.isLinearText = true
        textPaintPatient.isSubpixelText = true
        canvas.drawText("Hgb Haemoglobin", 20F, 157F, textPaintPatient)
        canvas.drawText("Hct Hematocrit", 20F, 167F, textPaintPatient)
        canvas.drawText("Hct/Hgb (Calculated)", 20F, 177F, textPaintPatient)
        canvas.drawText("MCHC (Calculated)", 20F, 187F, textPaintPatient)
        canvas.drawText("Hct Hematocrit", 20F, 197F, textPaintPatient)
        canvas.drawText("Hct/Hgb (Calculated)", 20F, 207F, textPaintPatient)

        //Results Counts
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(":   $hgb_haemoglobin_result", 97F, 157F, textPaintPatient)
        canvas.drawText(":   $hct_hematocrit_result", 97F, 167F, textPaintPatient)
        canvas.drawText(":   $hct_hgb_result", 97F, 177F, textPaintPatient)
        canvas.drawText(":   $mchc_result", 97F, 187F, textPaintPatient)
        canvas.drawText(":   $hct_hematocrit_result", 97F, 197F, textPaintPatient)
        canvas.drawText(":   $hct_hgb_result", 97F, 207F, textPaintPatient)

        //Units
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(hgb_haemoglobin_units, 130F, 157F, textPaintPatient)
        canvas.drawText(hct_hematocrit_unit, 130F, 167F, textPaintPatient)
        canvas.drawText(hct_hgb_units, 130F, 177F, textPaintPatient)
        canvas.drawText(mchc_units, 130F, 187F, textPaintPatient)
        canvas.drawText(hct_hematocrit_unit, 130F, 197F, textPaintPatient)
        canvas.drawText(hct_hgb_units, 130F, 207F, textPaintPatient)

        //Ref. Range
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(hgb_haemoglobin_ref_range, 168F, 157F, textPaintPatient)
        canvas.drawText(hct_hematocrit_ref_range, 168F, 167F, textPaintPatient)
        canvas.drawText(hct_hgb_ref_range, 168F, 177F, textPaintPatient)
        canvas.drawText(mchc_ref_range, 168F, 187F, textPaintPatient)
        canvas.drawText(hct_hematocrit_ref_range, 168F, 197F, textPaintPatient)
        canvas.drawText(hct_hgb_ref_range, 168F, 207F, textPaintPatient)

        //Total & Differencial Count
        paint.color = ContextCompat.getColor(context, R.color.grey)
        canvas.drawRect(10F, 217F, 237F, 227F, paint)
        textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
        textPaintPatient.typeface = customTypeFace3
        canvas.drawText("Total & Differencial Count", 95F, 224F, textPaintPatient)

        //Parameter Content
        textPaintPatient.typeface = customTypeFace3
        textPaintPatient.letterSpacing = 0.001f
        textPaintPatient.isAntiAlias = true
        textPaintPatient.isLinearText = true
        textPaintPatient.isSubpixelText = true
        canvas.drawText("White Blood Cell count", 20F, 239F, textPaintPatient)
        canvas.drawText("Neutrophill percent", 20F, 249F, textPaintPatient)
        canvas.drawText("Lymphocyte percent", 20F, 259F, textPaintPatient)
        canvas.drawText("Monocyte percent", 20F, 269F, textPaintPatient)
        canvas.drawText("Eosinophil percent", 20F, 279F, textPaintPatient)
        canvas.drawText("Basophil percent", 20F, 289F, textPaintPatient)
        canvas.drawText("Neutrophill absolute number", 20F, 299F, textPaintPatient)
        canvas.drawText("Lymphocyte absolute number", 20F, 309F, textPaintPatient)
        canvas.drawText("Monocyte absolute number", 20F, 319F, textPaintPatient)
        canvas.drawText("Eosinophill absoulte number", 20F, 329F, textPaintPatient)

        //Results Counts
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(":   $wbc_results", 97F, 239F, textPaintPatient)
        canvas.drawText(":   $neutrophil_percent_results", 97F, 249F, textPaintPatient)
        canvas.drawText(":   $lymphocyte_percent_results", 97F, 259F, textPaintPatient)
        canvas.drawText(":   $monocyte_percent_results", 97F, 269F, textPaintPatient)
        canvas.drawText(":   $eosinophil_percent_results", 97F, 279F, textPaintPatient)
        canvas.drawText(":   $basophil_percent_results", 97F, 289F, textPaintPatient)
        canvas.drawText(":   $neutrophil_absolute_num_results", 97F, 299F, textPaintPatient)
        canvas.drawText(":   $lymphocyte_absolute_num_results", 97F, 309F, textPaintPatient)
        canvas.drawText(":   $monocyte_absolute_num_results", 97F, 319F, textPaintPatient)
        canvas.drawText(":   $eosinophil_absolute_num_results", 97F, 329F, textPaintPatient)

        //Units
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(wbc_units, 130F, 239F, textPaintPatient)
        canvas.drawText(neutrophil_percent_units, 130F, 249F, textPaintPatient)
        canvas.drawText(lymphocyte_percent_units, 130F, 259F, textPaintPatient)
        canvas.drawText(monocyte_percent_units, 130F, 269F, textPaintPatient)
        canvas.drawText(eosinophil_percent_units, 130F, 279F, textPaintPatient)
        canvas.drawText(basophil_percent_units, 130F, 289F, textPaintPatient)
        canvas.drawText(neutrophil_absolute_num_units, 130F, 299F, textPaintPatient)
        canvas.drawText(lymphocyte_absolute_num_units, 130F, 309F, textPaintPatient)
        canvas.drawText(monocyte_absolute_num_units, 130F, 319F, textPaintPatient)
        canvas.drawText(eosinophil_absolute_num_units, 130F, 329F, textPaintPatient)

        //Ref. Range
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(wbc_ref_range, 168F, 239F, textPaintPatient)
        canvas.drawText(neutrophil_percent_ref_range, 168F, 249F, textPaintPatient)
        canvas.drawText(lymphocyte_percent_ref_range, 168F, 259F, textPaintPatient)
        canvas.drawText(monocyte_percent_ref_range, 168F, 269F, textPaintPatient)
        canvas.drawText(eosinophil_percent_ref_range, 168F, 279F, textPaintPatient)
        canvas.drawText(basophil_percent_ref_range, 168F, 289F, textPaintPatient)
        canvas.drawText(neutrophil_absolute_num_ref_range, 168F, 299F, textPaintPatient)
        canvas.drawText(lymphocyte_absolute_num_ref_range, 168F, 309F, textPaintPatient)
        canvas.drawText(monocyte_absolute_num_ref_range, 168F, 319F, textPaintPatient)
        canvas.drawText(eosinophil_absolute_num_ref_range, 168F, 329F, textPaintPatient)


        //Footer1
        paint.color = ContextCompat.getColor(context, R.color.custom_light_blue)
        canvas.drawRect(0F, 370F, 300F, 390F, paint)
        val xPos1 = (canvas.width / 5).toFloat()
        val textPaint1 = TextPaint()
        val customTypeFace6 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        textPaint1.color = ContextCompat.getColor(context, R.color.white)
        textPaint1.textSize = 6F
        textPaint1.typeface = customTypeFace6
        canvas.drawText(
            "+91 8508092626  |  support@mefy.care  |  CIN No. - 547474684656457",
            xPos1,
            383F,
            textPaint1
        )


        //Footer2
        paint.color = ContextCompat.getColor(context, R.color.custom_blue)
        canvas.drawRect(0F, 390F, 300F, 400F, paint)
        val xPos = (canvas.width / 4).toFloat()
        val textPaint = TextPaint()
        textPaint.color = ContextCompat.getColor(context, R.color.white)
        textPaint.textSize = 5.5F
        textPaint.typeface = customTypeFace6
        canvas.drawText(
            "This Prescription is automatically generated by MeFy Care",
            xPos,
            397F,
            textPaint
        )


        //Top Border of the Page
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 500F
        paint.color = ContextCompat.getColor(context, R.color.custom_blue)
        canvas.drawLine(10F, 0F, 10F, 10F, paint)
        pdfDocument.finishPage(myPage1)

        //End of Page 1

        //Start of Page 2
        val myPageInfo2 = PdfDocument.PageInfo.Builder(250, 400, 2).create()
        val myPage2 = pdfDocument.startPage(myPageInfo2) as PdfDocument.Page

        val newCanvas = myPage2.canvas as Canvas
        val newPaint = Paint()

        //Start of Top Header
        newPaint.color = ContextCompat.getColor(context, R.color.custom_blue)
        //val customTypeFace = ResourcesCompat.getFont(context, R.font.roboto_black)
        newPaint.typeface = customTypeFace
        newPaint.textSize = 14F
        newCanvas.drawText("Health Choice Clinic", 80F, 25F, newPaint)

        //val customTypeFace2 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        newPaint.typeface = customTypeFace2
        newPaint.letterSpacing = 0.001f
        newPaint.isAntiAlias = true
        newPaint.isLinearText = true
        newPaint.isSubpixelText = true
        newPaint.color = ContextCompat.getColor(context, R.color.black)
        newPaint.textSize = 5F
        newCanvas.drawText("8th floor Suratwala Mark Plazzo, Hinjawadi Pune", 80F, 35F, newPaint)


        newCanvas.drawLine(194F, 30F, 194F, 42F, newPaint)
        newPaint.typeface = customTypeFace2
        newPaint.textSize = 5F
        newCanvas.drawText("9452451241", 198F, 35F, newPaint)
        newCanvas.drawText("8245121416", 198F, 42F, newPaint)

        newPaint.color = ContextCompat.getColor(context, R.color.black)
        newPaint.textSize = 5F
        newCanvas.drawText("Maharastra, 41007", 80F, 40F, newPaint)

        newPaint.isAntiAlias = true
        newPaint.isFilterBitmap = true
        newPaint.isDither = true
        newCanvas.drawBitmap(
            BITMAP_RESIZER(bitmap, 50, 20)!!,
            10f,
            20f,
            Paint(Paint.FILTER_BITMAP_FLAG)
        )
        //End of Top Header

        //Start of Patient Detail Section
        newPaint.color = ContextCompat.getColor(context, R.color.custom_blue)
        newCanvas.drawRect(0F, 50F, 300F, 110F, newPaint)
        val textPaintPatientNew = TextPaint()
        textPaintPatientNew.typeface = customTypeFace4
        textPaintPatientNew.letterSpacing = 0.001f
        textPaintPatientNew.isAntiAlias = true
        textPaintPatientNew.isLinearText = true
        textPaintPatientNew.isSubpixelText = true
        textPaintPatientNew.color = ContextCompat.getColor(context, R.color.white)
        textPaintPatientNew.textSize = 6F
        newCanvas.drawText("Patient Name   :", 10F, 65F, textPaintPatientNew)
        newCanvas.drawText("Age & Gender   :", 10F, 75F, textPaintPatientNew)
        newCanvas.drawText("Referred by      :", 10F, 85F, textPaintPatientNew)
        newCanvas.drawText("Sample Type    :", 10F, 95F, textPaintPatientNew)

        textPaintPatientNew.typeface = customTypeFace5
        newCanvas.drawText(patientName, 60F, 65F, textPaintPatientNew)
        newCanvas.drawText("$patientAge Year/${patientGender}", 60F, 75F, textPaintPatientNew)
        newCanvas.drawText(referredDoctor, 60F, 85F, textPaintPatientNew)
        newCanvas.drawText(sampleType, 60F, 95F, textPaintPatientNew)

        newPaint.color = ContextCompat.getColor(context, R.color.white)
        newCanvas.drawLine(midX, 50F, midX, 110F, newPaint)

        textPaintPatientNew.typeface = Typeface.create("Roboto", Typeface.BOLD)
        newCanvas.drawText("Authorization Date   :", midX + 4F, 65F, textPaintPatientNew)
        newCanvas.drawText("Report Date   :", midX + 4F, 75F, textPaintPatientNew)
        newCanvas.drawText("Patient ID      :", midX + 4F, 85F, textPaintPatientNew)

        textPaintPatientNew.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        newCanvas.drawText(authorizationDate, midX + 62, 65F, textPaintPatientNew)
        newCanvas.drawText(reportDate, midX + 62, 75F, textPaintPatientNew)
        newCanvas.drawText(patientId.toString(), midX + 62, 85F, textPaintPatientNew)
        //End of the Patient Detail

        //Table of Test
        newPaint.color = ContextCompat.getColor(context, R.color.custom_light_blue)
        newCanvas.drawRect(10F, 115F, 80F, 125F, newPaint)
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
        textPaintPatient.color = ContextCompat.getColor(context, R.color.white)
        newCanvas.drawText("Parameter", 20F, 122F, textPaintPatient)

        newCanvas.drawRect(83F, 115F, 123F, 125F, newPaint)
        newCanvas.drawText("Results", 88F, 122F, textPaintPatient)

        newCanvas.drawRect(126F, 115F, 162F, 125F, newPaint)
        newCanvas.drawText("Units", 130F, 122F, textPaintPatient)

        newCanvas.drawRect(165F, 115F, 202F, 125F, newPaint)
        newCanvas.drawText("Ref. Range", 168F, 122F, textPaintPatient)

        newCanvas.drawRect(205F, 115F, 237F, 125F, newPaint)
        newCanvas.drawText("Barcode", 209F, 122F, textPaintPatient)

        newPaint.color = ContextCompat.getColor(context, R.color.custom_extra_light_blue)
        newCanvas.drawRect(10F, 125F, 237F, 135F, newPaint)

        textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
        newCanvas.drawText("Complete Blood Count", 95F, 132F, textPaintPatient)

        newPaint.color = ContextCompat.getColor(context, R.color.grey)
        newCanvas.drawRect(10F, 135F, 237F, 145F, newPaint)
        textPaintPatient.typeface = customTypeFace3
        textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
        newCanvas.drawText("Total & Differencial Count ", 90F, 142F, textPaintPatient)

        //Parameter Content
        textPaintPatient.typeface = customTypeFace3
        textPaintPatient.letterSpacing = 0.001f
        textPaintPatient.isAntiAlias = true
        textPaintPatient.isLinearText = true
        textPaintPatient.isSubpixelText = true
        newCanvas.drawText("Basophil absolute number", 20F, 157F, textPaintPatient)
        newCanvas.drawText("NLR(Calculated)", 20F, 167F, textPaintPatient)

        //Results
        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        newCanvas.drawText(":   $basophil_absolute_num_results", 97F, 157F, textPaintPatient)
        newCanvas.drawText(":   $nlr_calculated_results", 97F, 167F, textPaintPatient)

        //Units
        newCanvas.drawText(basophil_absolute_num_units, 130F, 157F, textPaintPatient)
        newCanvas.drawText(nlr_calculated_units, 130F, 167F, textPaintPatient)

        //Ref Range
        newCanvas.drawText(basophil_absolute_num_ref_range, 168F, 157F, textPaintPatient)
        newCanvas.drawText(nlr_calculated_ref_range, 168F, 167F, textPaintPatient)


        //Footer1
        newPaint.color = ContextCompat.getColor(context, R.color.custom_light_blue)
        newCanvas.drawRect(0F, 370F, 300F, 390F, newPaint)
        val xPos2 = (newCanvas.width / 5).toFloat()
        val textPaint2 = TextPaint()
        val customTypeFace7 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        textPaint2.color = ContextCompat.getColor(context, R.color.white)
        textPaint2.textSize = 6F
        textPaint2.typeface = customTypeFace7
        newCanvas.drawText(
            "+91 8508092626  |  support@mefy.care  |  CIN No. - 547474684656457",
            xPos2,
            383F,
            textPaint2
        )


       // Footer2
        newPaint.color = ContextCompat.getColor(context, R.color.custom_blue)
        newCanvas.drawRect(0F, 390F, 300F, 400F, newPaint)
        val xPos3 = (newCanvas.width / 4).toFloat()
        val textPaint3 = TextPaint()
        textPaint3.color = ContextCompat.getColor(context, R.color.white)
        textPaint3.textSize = 5.5F
        textPaint3.typeface = customTypeFace7
        newCanvas.drawText(
            "This Prescription is automatically generated by MeFy Care",
            xPos3,
            397F,
            textPaint3
        )

        //Top Border of the Page
        newPaint.style = Paint.Style.STROKE
        newPaint.strokeWidth = 500F
        newPaint.color = ContextCompat.getColor(context, R.color.custom_blue)
        newCanvas.drawLine(10F, 0F, 10F, 10F, paint)
        pdfDocument.finishPage(myPage2)

        //Writing File to the External Storage
        if (isExternalStorageWritable()) {
            val file = File(Environment.getExternalStorageDirectory(), "/NewTest.pdf")
            try {
                pdfDocument.writeTo(FileOutputStream(file))
                pdfDocument.close()
                Toast.makeText(
                    context,
                    "File is successfully saved ${file.path}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } catch (e: IOException) {
                Toast.makeText(
                    context,
                    "File is not saved ${e.localizedMessage}",
                    Toast.LENGTH_SHORT
                )
                    .show()
                e.printStackTrace()
            }
        } else {
            Toast.makeText(context, "Can't save to external storage", Toast.LENGTH_SHORT).show()
            pdfDocument.close()
        }
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }


    fun BITMAP_RESIZER(bitmap: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
        val scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888)
        val ratioX = newWidth / bitmap.width.toFloat()
        val ratioY = newHeight / bitmap.height.toFloat()
        val middleX = newWidth / 2.0f
        val middleY = newHeight / 2.0f
        val scaleMatrix = Matrix()
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
        val canvas = Canvas(scaledBitmap)
        canvas.setMatrix(scaleMatrix)
        canvas.drawBitmap(
            bitmap,
            middleX - bitmap.width / 2,
            middleY - bitmap.height / 2,
            Paint(Paint.FILTER_BITMAP_FLAG)
        )
        return scaledBitmap
    }

    fun getBitmapFromVectorDrawable(context: Context?, drawableId: Int): Bitmap? {
        var drawable = ContextCompat.getDrawable(context!!, drawableId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

}