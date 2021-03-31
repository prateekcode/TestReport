package com.example.testreport.helper

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.testreport.R
import com.example.testreport.model.LastValue
import com.example.testreport.model.NewPatient


fun testTableContent(context: Context, newPatient: NewPatient, paint: Paint, canvas: Canvas): LastValue {
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
        canvas.drawText(
            newPatient.sampleType.conditionList[i].condtionHeader,
            95F,
            132F,
            textPaintPatient
        )
        Log.d("GLOBALY", "Value of Global Y is $globalY and overall value is ${142f + globalY}")


        //Return last length
        //Condition Name
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
                    if (globalY <= 330f){
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
                    }else{
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
                    canvas.width / 2.7f,
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
                        Log.d(TAG, "testTableContent: The last value of the i is $parameter")
                        lastValueOfI = parameter
                        Log.d(TAG, "testTableContent: The last value of the i is $lastValueOfI")
                        //lastValue.lastValueOfI= parameter
                        Log.d(TAG, "testTableContent: The last value of newY is $newY ")
                        lastHeight = newY
                        Log.d(TAG, "testTableContent: The last value of newY is $lastHeight ")
                        //lastValue.lastHeight = newY
                    }else if (newY > 330f){
                        //create a new page and shift the remaining item into that one

                    } else {
                        break
                    }

                }

                Log.d(TAG, "testTableContent: The last value of the outside loop i is $lastValueOfI")
                Log.d(TAG, "testTableContent: The last value of the outside loop newY is $lastHeight ")

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
    Log.d(TAG, "testTableContent: The last value of the i at the end is $lastValueOfI")
    lastValue.lastValueOfI = lastValueOfI
    Log.d(TAG, "testTableContent: ${lastValue.lastValueOfI}")
    Log.d(TAG, "testTableContent: The last value of the newY at the end is $lastHeight ")
    lastValue.lastHeight = lastHeight
    Log.d(TAG, "testTableContent: ${lastValue.lastHeight}")
    return lastValue
}