package com.example.testreport.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.testreport.R
import com.example.testreport.model.NewPatient
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object NewPdfCreator {

    fun createFreshTestReport(
        context: Context,
        bitmap: Bitmap,
        newPatient: NewPatient
    ) {
        val newPdfDocument = PdfDocument()
        val pageInfoNew = PdfDocument.PageInfo.Builder(250, 400, 1).create()
        val page1 = newPdfDocument.startPage(pageInfoNew) as PdfDocument.Page
        val paint = Paint()
        val canvas = page1.canvas as Canvas

        //Start of Top Header (Hospital Details)  (TYPE - STATIC)
        paint.color = ContextCompat.getColor(context, R.color.custom_blue)
        val customTypeFace = ResourcesCompat.getFont(context, R.font.roboto_black)
        paint.typeface = customTypeFace
        paint.textSize = 14F
        canvas.drawText("Mars Mission Alien Clinic", 80F, 25F, paint)

        val customTypeFace2 = ResourcesCompat.getFont(context, R.font.roboto_medium)
        paint.typeface = customTypeFace2
        paint.letterSpacing = 0.001f
        paint.isAntiAlias = true
        paint.isLinearText = true
        paint.isSubpixelText = true
        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.textSize = 5F
        canvas.drawText("ABC Place not Exist in this world", 80F, 35F, paint)

        canvas.drawLine(194F, 30F, 194F, 42F, paint)
        paint.typeface = customTypeFace2
        paint.textSize = 5F
        canvas.drawText("xxxxxxxxxx", 198F, 35F, paint)
        canvas.drawText("xxxxxxxxxx", 198F, 42F, paint)

        paint.color = ContextCompat.getColor(context, R.color.black)
        paint.textSize = 5F
        canvas.drawText("Mars", 80F, 40F, paint)

        paint.isAntiAlias = true
        paint.isFilterBitmap = true
        paint.isDither = true

        //LOGO of the company
        canvas.drawBitmap(
            PdfCreator.BITMAP_RESIZER(bitmap, 30, 20)!!,
            10f,
            0f,
            paint
        )
        //End of Top Header (Hospital Details)  (TYPE - STATIC)

        //Start of PATIENT Detail Section
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
        canvas.drawText(newPatient.patientName, 60F, 65F, textPaintPatient)
        canvas.drawText("${newPatient.age} Year/${newPatient.gender}", 60F, 75F, textPaintPatient)
        canvas.drawText(newPatient.referredDoctor, 60F, 85F, textPaintPatient)
        canvas.drawText(newPatient.sampleType.sampleTypeName, 60F, 95F, textPaintPatient)

        paint.color = ContextCompat.getColor(context, R.color.white)
        val midX = (canvas.width / 2).toFloat()
        canvas.drawLine(midX, 50F, midX, 110F, paint)

        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
        canvas.drawText("Authorization Date   :", midX + 4F, 65F, textPaintPatient)
        canvas.drawText("Report Date   :", midX + 4F, 75F, textPaintPatient)
        canvas.drawText("Patient ID      :", midX + 4F, 85F, textPaintPatient)

        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
        canvas.drawText(newPatient.authorizationDate, midX + 62, 65F, textPaintPatient)
        canvas.drawText(newPatient.reportDate, midX + 62, 75F, textPaintPatient)
        canvas.drawText(newPatient.patientId, midX + 62, 85F, textPaintPatient)
        //End of the Patient Detail

        //Table of Test

        // Top Header (Static Type)
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

        //End of Top Header(Static Type)
        var globalY: Float = 0f
        for (i in newPatient.sampleType.conditionList.indices) {

            //Condition Header
            paint.color = ContextCompat.getColor(context, R.color.custom_extra_light_blue)
            canvas.drawRect(10F, 125F, 237F, 135F, paint)
            textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
            canvas.drawText(
                newPatient.sampleType.conditionList[i].condtionHeader,
                95F,
                132F,
                textPaintPatient
            )
            Log.d("GLOBALY", "Value of Global Y is $globalY and overall value is ${142f + globalY}")

            //Condition Name
            val customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium)
            textPaintPatient.typeface = customTypeFace3
            textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
            if (i == 0) {
                paint.color = ContextCompat.getColor(context, R.color.light_grey)
                canvas.drawRect(10F, 135F, 237F, 145F, paint)   //Border of condition name
                textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
                canvas.drawText(
                    newPatient.sampleType.conditionList[i].conditionName,
                    115F,
                    142F,
                    textPaintPatient
                )
                //Text of condition name inside the border
                //Parameter Content
                textPaintPatient.letterSpacing = 0.001f
                textPaintPatient.isAntiAlias = true
                textPaintPatient.isLinearText = true
                textPaintPatient.isSubpixelText = true
                for (parameter in newPatient.sampleType.conditionList[i].conditionTypeList.indices) {
                    globalY = 147f + ((parameter * 10) + 10).toFloat()
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterName,
                        20F,
                        globalY,
                        textPaintPatient
                    )
                    Log.d(
                        "GLOBALYNEW",
                        "Value of Global Y is $globalY and overall value is ${142f + globalY}"
                    )
                    //Results
                    canvas.drawText(
                        ":   ${newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.results}",
                        97F,
                        globalY,
                        textPaintPatient
                    )
                    //Units
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.units,
                        135F,
                        globalY,
                        textPaintPatient
                    )
                    //Ref Range
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.ref_range,
                        173F,
                        globalY,
                        textPaintPatient
                    )
                }
            } else {
                paint.color = ContextCompat.getColor(context, R.color.light_grey)
                canvas.drawRect(
                    10F,
                    globalY + 13f,
                    237F,
                    globalY + 23f,
                    paint
                )   //Border of condition name
                canvas.drawText(
                    newPatient.sampleType.conditionList[i].conditionName,
                    canvas.width / 2.7f,
                    globalY + 20f,
                    textPaintPatient
                )
                //Text of condition name inside the border
                for (parameter in newPatient.sampleType.conditionList[i].conditionTypeList.indices) {
                    //globalY =  ((new * 10) + 10).toFloat()
                    val newY = globalY + ((parameter * 10) + 10).toFloat()
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterName,
                        20F,
                        newY + 25f,
                        textPaintPatient
                    )
                    //Results
                    canvas.drawText(
                        ":   ${newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.results}",
                        97F,
                        newY + 25f,
                        textPaintPatient
                    )
                    //Units
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.units,
                        135F,
                        newY + 25f,
                        textPaintPatient
                    )
                    //Ref Range
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.ref_range,
                        173F,
                        newY + 25f,
                        textPaintPatient
                    )
                }
            }


        }

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
        textPaint.letterSpacing = 0.001f
        textPaint.isAntiAlias = true
        textPaint.isLinearText = true
        textPaint.isSubpixelText = true
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

        //End of the pdf
        newPdfDocument.finishPage(page1)


        //Writing File to the External Storage
        if (isExternalStorageWritable()) {
            val file = File(Environment.getExternalStorageDirectory(), "/NewTest.pdf")
            //val fileOutputStream: FileOutputStream

            try {
                //fileOutputStream = context.openFileOutput("NewTest.pdf", MODE_PRIVATE)
                //fileOutputStream.write(pdfDocument.pages)
                newPdfDocument.writeTo(FileOutputStream(file))
                newPdfDocument.close()
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
            newPdfDocument.close()
        }

    }

    private fun isExternalStorageWritable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }
}