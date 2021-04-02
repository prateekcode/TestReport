package com.example.testreport.freshcreation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.testreport.R
import com.example.testreport.model.LastValue
import com.example.testreport.model.NewPatient

object ConditionalPageCreation {

    fun conditionalPageCreation(
        context: Context,
        newPatient: NewPatient,
        bitmap: Bitmap
    ) {
        val createThePdfDocument = PdfDocument()
        var newValueOfY = 0f
        var pageNumber = 0




        for (pageNo in newPatient.sampleType.conditionList.indices) {
            val page = createThePdfDocument.startPage(pageInfoHelper(pageNo))
            val canvas = page.canvas

            PaintPage.headerPaint(context, bitmap, canvas)
            PaintPage.patientDetail(context, canvas, newPatient)
            PaintPage.elementHeaderTable(context, canvas)

            val textPaint = TextPaint()
            val paint = Paint()

            for (i in newPatient.sampleType.conditionList.indices) {

                Log.d(TAG, "SIZE OF CONDITION LIST --> ${newPatient.sampleType.conditionList.size}")


                if (newValueOfY > 330f) {
                    createThePdfDocument.pages[pageNumber++]
                    textPaintHelper(textPaint, 6f)
                    textPaint.textAlign = Paint.Align.CENTER
                    Log.d(TAG, "The New Value of Y is $newValueOfY")
                    page.info.pageNumber
                    PaintPage.pathologistDetail(context, page.canvas)
                    //Yet to implement
                    //need to write in the pages that left
                    //if the page1 occupied with the height more than 330f then using conditional write on next pages
                    Log.d(TAG, "conditionalPageCreation: HEIGHT INCREASED")
                }
                newValueOfY = 125f
                textPaintHelper(textPaint, 6f)
                paint.color = ContextCompat.getColor(context, R.color.custom_extra_light_blue)
                canvas.drawRect(10F, newValueOfY, 237F, newValueOfY + 10f, paint)
                textPaint.textAlign = Paint.Align.CENTER
                textPaint.color = ContextCompat.getColor(context, R.color.black)
                canvas.drawText(
                    newPatient.sampleType.conditionList[i].condtionHeader,
                    canvas.width / 2f,
                    newValueOfY + 7f,
                    textPaint
                )
                newValueOfY += 16.5f
                val customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium)
                textPaint.typeface = customTypeFace3
                textPaint.color = ContextCompat.getColor(context, R.color.black)
                paint.color = ContextCompat.getColor(context, R.color.light_grey)
                //canvas.drawRect(10F, 135F, 237F, 145F, paint)   //Border of condition name
                textPaint.color = ContextCompat.getColor(context, R.color.black)
                textPaint.textAlign = Paint.Align.CENTER
                Log.d(
                    TAG,
                    "CONDITION NAME of ${newPatient.patientName}: ${newPatient.sampleType.conditionList[i].conditionName}"
                )
                Log.d(TAG, "Checking the newvalueofY here $newValueOfY")
                if (newValueOfY < 330f) {
                    newValueOfY *= (i + 1)
                    canvas.drawRect(10F, newValueOfY - 7f, 237F, newValueOfY + 3f, paint)
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionName,
                        canvas.width / 2f,
                        newValueOfY,
                        textPaint
                    )
                    //var yInParameter = newValueOfY
                    for (parameter in newPatient.sampleType.conditionList[i].conditionTypeList.indices) {
                        textPaint.textAlign = Paint.Align.LEFT
                        val yInParameter = newValueOfY + 5f + ((parameter * 10) + 10).toFloat()
                        //newValueOfY += 5f + ((parameter * 10) + 10).toFloat()
                        if (newValueOfY < 330f && yInParameter < 360f) {
                            canvas.drawText(
                                newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterName,
                                20F,
                                yInParameter,
                                textPaint
                            )
                            //Results
                            canvas.drawText(
                                ":   ${newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.results}",
                                97F,
                                yInParameter,
                                textPaint
                            )
                            //Units
                            canvas.drawText(
                                newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.units,
                                135F,
                                yInParameter,
                                textPaint
                            )
                            //Ref Range
                            canvas.drawText(
                                newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.ref_range,
                                173F,
                                yInParameter,
                                textPaint
                            )

                        } else {
                            break
                        }
//                            newValueOfY = yInParameter
                    }
                } else {
                    break




                }
            }

            //PaintPage.pathologistDetail(context, page.canvas)
            PaintPage.footerPaint(context, page.canvas)
            createThePdfDocument.finishPage(page)


        }
        //Writing File to the External Storage
        WritingToStorage.writingToExternalStorage(context, createThePdfDocument, newPatient)
    }

    //Element Table Content
    fun elementTableContent(
        context: Context,
        canvas: Canvas,
        newPatient: NewPatient,
    ): Float {
        var textPaint = TextPaint()
        var downYStartPoint = 125f
        //if downYStartPoint is greater than 330f then page will be changes
        //so first thing need to return is downYStartPoint last value in the end
        //of the process

        downYStartPoint = 340f

        //if arrayList height > 350f return downy
        //else return downYStart

        //downYStartPoint = 220f

        return downYStartPoint
    }

    fun testTableContent(
        context: Context,
        newPatient: NewPatient,
        paint: Paint,
        canvas: Canvas
    ): LastValue {
        var globalY: Float = 0f
        var newY: Float = 0f
        val lastValue = LastValue()
        var lastValueOfI: Int = 0
        var lastHeight: Float = 0f
        val textPaintPatient = TextPaint()
        for (i in newPatient.sampleType.conditionList.indices) {

            textPaintPatient.textSize = 6f

            //Condition Header
            paint.color = ContextCompat.getColor(context, R.color.custom_extra_light_blue)
            canvas.drawRect(10F, 125F, 237F, 135F, paint)
            textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
            val middleValueOfX = (237f - 10f) / 2.4f
            canvas.drawText(
                newPatient.sampleType.conditionList[i].condtionHeader,
                middleValueOfX,
                132F,
                textPaintPatient
            )
            Log.d("GLOBALY", "Value of Global Y is $globalY and overall value is ${142f + globalY}")


            //Return last length
            //Condition Name
            textPaintPatient.letterSpacing = 0.001f
            textPaintPatient.isAntiAlias = true
            textPaintPatient.isLinearText = true
            textPaintPatient.isSubpixelText = true
            val customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium)
            textPaintPatient.typeface = customTypeFace3
            textPaintPatient.color = ContextCompat.getColor(context, R.color.black)

            when (i) {
                0 -> {
                    paint.color = ContextCompat.getColor(context, R.color.light_grey)
                    canvas.drawRect(10F, 135F, 237F, 145F, paint)   //Border of condition name
                    textPaintPatient.color = ContextCompat.getColor(context, R.color.black)
                    canvas.drawText(
                        newPatient.sampleType.conditionList[i].conditionName,
                        (237f - 10f) / 2,
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
                        if (globalY <= 330f) {
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
                        } else {
                            break
                        }
                    }
                }
                1 -> {
                    Log.d("GLOABLY1", "$globalY")
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
                        middleValueOfX,
                        globalY + 20f,
                        textPaintPatient
                    )
                    //Text of condition name inside the border
                    for (parameter in newPatient.sampleType.conditionList[i].conditionTypeList.indices) {
                        if (newY <= 330F) {
                            //globalY =  ((new * 10) + 10).toFloat()
                            newY = globalY + ((parameter * 10) + 10).toFloat()
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
                            Log.d(
                                ContentValues.TAG,
                                "testTableContent: The last value of the i is $parameter"
                            )
                            lastValueOfI = parameter
                            Log.d(
                                ContentValues.TAG,
                                "testTableContent: The last value of the i is $lastValueOfI"
                            )
                            //lastValue.lastValueOfI= parameter
                            Log.d(
                                ContentValues.TAG,
                                "testTableContent: The last value of newY is $newY "
                            )
                            lastHeight = newY
                            Log.d(
                                ContentValues.TAG,
                                "testTableContent: The last value of ${newPatient.patientName} newY is $lastHeight "
                            )
                            //lastValue.lastHeight = newY
                        } else if (newY > 330f) {
                            //create a new page and shift the remaining item into that one
                            //testTableContent2(context, newPatient, paint, canvas)
                        } else {
                            break
                        }

                    }

                    Log.d(
                        ContentValues.TAG,
                        "testTableContent: The last value of the outside loop i is $lastValueOfI"
                    )
                    Log.d(
                        ContentValues.TAG,
                        "testTableContent: The last value of ${newPatient.patientName} the outside loop newY is $lastHeight "
                    )

                }

//            2 -> {
//                paint.color = ContextCompat.getColor(context, R.color.light_grey)
//                Log.d("GLOABLY2", "$globalY")
//                canvas.drawRect(
//                    10F,
//                    newY + 13f,
//                    237F,
//                    newY + 23f,
//                    paint
//                )   //Border of condition name
//                canvas.drawText(
//                    newPatient.sampleType.conditionList[i].conditionName,
//                    canvas.width / 2.7f,
//                    newY + 20f,
//                    textPaintPatient
//                )
//                //Text of condition name inside the border
//                for (parameter in newPatient.sampleType.conditionList[i].conditionTypeList.indices) {
//                    //globalY =  ((new * 10) + 10).toFloat()
//                    val newY2 = newY + ((parameter * 10) + 10).toFloat()
//                    canvas.drawText(
//                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterName,
//                        20F,
//                        newY2 + 25f,
//                        textPaintPatient
//                    )
//                    //Results
//                    canvas.drawText(
//                        ":   ${newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.results}",
//                        97F,
//                        newY2 + 25f,
//                        textPaintPatient
//                    )
//                    //Units
//                    canvas.drawText(
//                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.units,
//                        135F,
//                        newY2 + 25f,
//                        textPaintPatient
//                    )
//                    //Ref Range
//                    canvas.drawText(
//                        newPatient.sampleType.conditionList[i].conditionTypeList[parameter].parameterResponse.ref_range,
//                        173F,
//                        newY2 + 25f,
//                        textPaintPatient
//                    )
//                }
//            }
            }

        }
        Log.d(
            ContentValues.TAG,
            "testTableContent: The last value of the i at the end is $lastValueOfI"
        )
        lastValue.lastValueOfI = lastValueOfI
        Log.d(ContentValues.TAG, "testTableContent: ${lastValue.lastValueOfI}")
        Log.d(
            ContentValues.TAG,
            "testTableContent: The last value of the newY at the end is $lastHeight "
        )
        lastValue.lastHeight = lastHeight
        Log.d(
            ContentValues.TAG,
            "testTableContent of ${newPatient.patientName}: ${lastValue.lastHeight}"
        )
        return lastValue
    }

    private fun pageInfoHelper(pageNumber: Int): PdfDocument.PageInfo? {
        return PdfDocument
            .PageInfo
            .Builder(250, 400, pageNumber)
            .create()
    }

    private fun textPaintHelper(textPaint: TextPaint, textSize: Float): TextPaint {
        textPaint.textSize = textSize
        textPaint.letterSpacing = 0.001f
        textPaint.isAntiAlias = true
        textPaint.isLinearText = true
        textPaint.isSubpixelText = true
        return textPaint
    }


}