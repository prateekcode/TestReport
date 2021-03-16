package com.example.testreport

import android.Manifest
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.text.TextPaint
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.testreport.helper.PdfCreator
import com.example.testreport.helper.PermissionHelper
import com.example.testreport.model.Parameter
import com.example.testreport.model.ParameterResponse
import com.example.testreport.model.Patient
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var isGenerating = false
    private lateinit var bitmap: Bitmap
    private lateinit var scaledBitmap: Bitmap
    val pdfCreator = PdfCreator

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val options = BitmapFactory.Options()
        options.inDither = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.logo, options)
        //bitmap = pdfCreator.getBitmapFromVectorDrawable(applicationContext, R.drawable.ic_mobile)!!
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 70, 20, false)
        //scaledBitmap = pdfCreator.BITMAP_RESIZER(bitmap, 200, 18)!!

        val patientDetail = arrayOf<Patient>(
            Patient(
                "Array Kumar",
                32,
                "Male",
                "Dr. Kotlin Generic",
                "EDTA_Blood",
                1234,
                Parameter(
                    hgb_haemoglobin = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hematocrit = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hgb = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    mchc = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    )

                )
            ),
            Patient(
                "ForLoop Kumar",
                22,
                "Male",
                "Dr. Kotlin Generic",
                "EDTA_Blood",
                        4567,
                Parameter(
                    hgb_haemoglobin = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hematocrit = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hgb = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    mchc = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    )

                )
            ),
            Patient(
                "MainActivity Saheb",
                52,
                "Male",
                "Dr. Kotlin Generic",
                "EDTA_Blood",
                7890,
                Parameter(
                    hgb_haemoglobin = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hematocrit = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hgb = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    mchc = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    )

                )
            ),
            Patient(
                "DetailFragment Dada",
                82,
                "Male",
                "Dr. Kotlin Generic",
                "EDTA_Blood",
                8932,
                Parameter(
                    hgb_haemoglobin = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hematocrit = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hgb = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    mchc = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    )

                )
            ),
            Patient(
                "Array Kumar",
                32,
                "Male",
                "Dr. Kotlin Generic",
                "EDTA_Blood",
                3467,
                Parameter(
                    hgb_haemoglobin = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hematocrit = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    hct_hgb = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    ),
                    mchc = ParameterResponse(
                        18,
                        "g/dL",
                        "14-18"
                    )

                )
            ),
        )

        permissionCheck(false)
        buttonSavePdf.setOnClickListener {
            permissionCheck(true)
            if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                //createNewPdf()
                for (x in patientDetail){
                    pdfCreator.createTestReportPdf(
                        applicationContext,
                        scaledBitmap,
                        x.patient_name,
                        x.patient_age,
                        x.patient_gender,
                        x.referred_doctor,
                        x.sample_type,
                        x.patient_id,
                        "16/03/2021   10:52 am",
                        "22/03/2021   10:00 am"
                    )
                }
            }
        }
    }

    private fun permissionCheck(check: Boolean) {
        val permissionHelper = PermissionHelper(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            100
        )
        permissionHelper.denied {
            if (it) {
                Log.d("Permission check", "Permission denied by system")
                Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show()
                permissionHelper.openAppDetailsActivity()
            } else {
                Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show()
                Log.d("Permission check", "Permission denied")
            }
        }
        permissionHelper.requestAll {
            Log.d("Permission check", "All permission granted")

            if (!isGenerating && check) {
                isGenerating = true

                val handler = Handler()
                val runnable = Runnable {
                    //to avoid multiple generation at the same time. Set isGenerating = false on some delay
                    isGenerating = false
                }
                handler.postDelayed(runnable, 2000)
                //  Toast.makeText(this, "Permission Has Been Granted", Toast.LENGTH_SHORT).show()
            }

        }
        permissionHelper.requestIndividual {
            Log.d("Permission check", "Individual Permission Granted")
        }
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun createNewPdf() {
//        val pdfDocument = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            PdfDocument()
//        } else {
//            TODO("VERSION.SDK_INT < KITKAT")
//        }
//        val myPageInfo = PdfDocument.PageInfo.Builder(250, 400, 1).create()
//        val myPage1 = pdfDocument.startPage(myPageInfo) as PdfDocument.Page
//
//        val paint = Paint()
//        val canvas = myPage1.canvas as Canvas
//
//
//        //Start of Top Header
//        paint.color = getColor(R.color.extra_dark_blue)
//
//        //paint.typeface = Typeface.create("Roboto", Typeface.BOLD)
//        val customTypeFace = ResourcesCompat.getFont(applicationContext, R.font.roboto_black)
//        paint.typeface = customTypeFace
//        paint.textSize = 14F
//        canvas.drawText("Health Choice Clinic", 80F, 25F, paint)
//
//        val customTypeFace2 = ResourcesCompat.getFont(applicationContext, R.font.roboto_medium)
//        paint.typeface = customTypeFace2
//        paint.letterSpacing = 0.001f
//        paint.isAntiAlias = true
//        paint.isLinearText = true
//        paint.isSubpixelText = true
//        paint.color = getColor(R.color.black)
//        paint.textSize = 5F
//        canvas.drawText("8th floor Suratwala Mark Plazzo, Hinjawadi Pune", 80F, 35F, paint)
//
//
//        canvas.drawLine(194F, 30F, 194F, 42F, paint)
//        paint.typeface = customTypeFace2
//        paint.textSize = 5F
//        canvas.drawText("9452451241", 198F, 35F, paint)
//        canvas.drawText("8245121416", 198F, 42F, paint)
//
//        paint.color = getColor(R.color.black)
//        paint.textSize = 5F
//        canvas.drawText("Maharastra, 41007", 80F, 40F, paint)
//
//        paint.isAntiAlias = true
//        paint.isFilterBitmap = true
//        paint.isDither = true
//        canvas.drawBitmap(scaledBitmap, 10f, 20f, Paint(Paint.FILTER_BITMAP_FLAG))
//        //End of Top Header
//
//        //Start of Patient Detail Section
//        paint.color = getColor(R.color.purple_700)
//        canvas.drawRect(0F, 50F, 300F, 110F, paint)
//        val textPaintPatient = TextPaint()
//        val customTypeFace4 = ResourcesCompat.getFont(applicationContext, R.font.roboto_bold)
//        textPaintPatient.typeface = customTypeFace4
//        textPaintPatient.letterSpacing = 0.001f
//        textPaintPatient.isAntiAlias = true
//        textPaintPatient.isLinearText = true
//        textPaintPatient.isSubpixelText = true
//        textPaintPatient.color = getColor(R.color.white)
//        textPaintPatient.textSize = 6F
//        canvas.drawText("Patient Name   :", 10F, 65F, textPaintPatient)
//        canvas.drawText("Age & Gender   :", 10F, 75F, textPaintPatient)
//        canvas.drawText("Referred by      :", 10F, 85F, textPaintPatient)
//        canvas.drawText("Sample Type    :", 10F, 95F, textPaintPatient)
//
//        val customTypeFace5 = ResourcesCompat.getFont(applicationContext, R.font.roboto)
//        textPaintPatient.typeface = customTypeFace5
//        canvas.drawText("Patient Name", 60F, 65F, textPaintPatient)
//        canvas.drawText("27 Year/Male", 60F, 75F, textPaintPatient)
//        canvas.drawText("Dr. Aditya Biswas", 60F, 85F, textPaintPatient)
//        canvas.drawText("EDTA Blood", 60F, 95F, textPaintPatient)
//
//        paint.color = getColor(R.color.white)
//        val midX = (canvas.width / 2).toFloat()
//        canvas.drawLine(midX, 50F, midX, 110F, paint)
//
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
//        canvas.drawText("Authorization Date   :", midX + 4F, 65F, textPaintPatient)
//        canvas.drawText("Report Date   :", midX + 4F, 75F, textPaintPatient)
//        canvas.drawText("Patient ID      :", midX + 4F, 85F, textPaintPatient)
//
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText("03/03/2021   10:30 am", midX + 62, 65F, textPaintPatient)
//        canvas.drawText("03/03/2021   10:30 am", midX + 62, 75F, textPaintPatient)
//        canvas.drawText("7653", midX + 62, 85F, textPaintPatient)
//        //End of the Patient Detail
//
//
//        //Table of Test
//        paint.color = getColor(R.color.dark_blue)
//        canvas.drawRect(10F, 115F, 80F, 125F, paint)
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.BOLD)
//        canvas.drawText("Parameter", 20F, 122F, textPaintPatient)
//
//        canvas.drawRect(83F, 115F, 123F, 125F, paint)
//        canvas.drawText("Results", 88F, 122F, textPaintPatient)
//
//        canvas.drawRect(126F, 115F, 162F, 125F, paint)
//        canvas.drawText("Units", 130F, 122F, textPaintPatient)
//
//        canvas.drawRect(165F, 115F, 202F, 125F, paint)
//        canvas.drawText("Ref. Range", 168F, 122F, textPaintPatient)
//
//        canvas.drawRect(205F, 115F, 237F, 125F, paint)
//        canvas.drawText("Barcode", 209F, 122F, textPaintPatient)
//
//        paint.color = getColor(R.color.blue_light)
//        canvas.drawRect(10F, 125F, 237F, 135F, paint)
//        textPaintPatient.color = getColor(R.color.black)
//        canvas.drawText("Complete Blood Count", 95F, 132F, textPaintPatient)
//
//        paint.color = getColor(R.color.grey)
//        canvas.drawRect(10F, 135F, 237F, 145F, paint)
//        val customTypeFace3 = ResourcesCompat.getFont(applicationContext, R.font.roboto_medium)
//        textPaintPatient.typeface = customTypeFace3
//        textPaintPatient.color = getColor(R.color.black)
//        canvas.drawText("Anaemia", 115F, 142F, textPaintPatient)
//
//        //Parameter Content
//        textPaintPatient.letterSpacing = 0.001f
//        textPaintPatient.isAntiAlias = true
//        textPaintPatient.isLinearText = true
//        textPaintPatient.isSubpixelText = true
//        canvas.drawText("Hgb Haemoglobin", 20F, 157F, textPaintPatient)
//        canvas.drawText("Hct Hematocrit", 20F, 167F, textPaintPatient)
//        canvas.drawText("Hct/Hgb (Calculated)", 20F, 177F, textPaintPatient)
//        canvas.drawText("MCHC (Calculated)", 20F, 187F, textPaintPatient)
//        canvas.drawText("Hct Hematocrit", 20F, 197F, textPaintPatient)
//        canvas.drawText("Hct/Hgb (Calculated)", 20F, 207F, textPaintPatient)
//
//        //Results Counts
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText(":   15", 97F, 157F, textPaintPatient)
//        canvas.drawText(":   45", 97F, 167F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 177F, textPaintPatient)
//        canvas.drawText(":   35", 97F, 187F, textPaintPatient)
//        canvas.drawText(":   45", 97F, 197F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 207F, textPaintPatient)
//
//        //Units
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText("g/dL", 130F, 157F, textPaintPatient)
//        canvas.drawText("%", 130F, 167F, textPaintPatient)
//        canvas.drawText("", 130F, 177F, textPaintPatient)
//        canvas.drawText("g/dL", 130F, 187F, textPaintPatient)
//        canvas.drawText("%", 130F, 197F, textPaintPatient)
//        canvas.drawText("", 130F, 207F, textPaintPatient)
//
//        //Ref. Range
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText("14-18", 168F, 157F, textPaintPatient)
//        canvas.drawText("45-54", 168F, 167F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 177F, textPaintPatient)
//        canvas.drawText("32.5-36", 168F, 187F, textPaintPatient)
//        canvas.drawText("42-54", 168F, 197F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 207F, textPaintPatient)
//
//        //Total & Differencial Count
//        paint.color = getColor(R.color.grey)
//        canvas.drawRect(10F, 217F, 237F, 227F, paint)
//        textPaintPatient.color = getColor(R.color.black)
//        textPaintPatient.typeface = customTypeFace3
//        canvas.drawText("Total & Differencial Count", 95F, 224F, textPaintPatient)
//
//        //Parameter Content
//        textPaintPatient.typeface = customTypeFace3
//        textPaintPatient.letterSpacing = 0.001f
//        textPaintPatient.isAntiAlias = true
//        textPaintPatient.isLinearText = true
//        textPaintPatient.isSubpixelText = true
//        canvas.drawText("White Blood Cell count", 20F, 239F, textPaintPatient)
//        canvas.drawText("Neutrophill percent", 20F, 249F, textPaintPatient)
//        canvas.drawText("Lymphocyte percent", 20F, 259F, textPaintPatient)
//        canvas.drawText("Monocyte percent", 20F, 269F, textPaintPatient)
//        canvas.drawText("Eosinophil percent", 20F, 279F, textPaintPatient)
//        canvas.drawText("Basophil percent", 20F, 289F, textPaintPatient)
//        canvas.drawText("Neutrophill absolute number", 20F, 299F, textPaintPatient)
//        canvas.drawText("Lymphocyte absolute number", 20F, 309F, textPaintPatient)
//        canvas.drawText("Monocyte absolute number", 20F, 319F, textPaintPatient)
//        canvas.drawText("Eosinophill absoulte number", 20F, 329F, textPaintPatient)
//
//        //Results Counts
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText(":   15", 97F, 239F, textPaintPatient)
//        canvas.drawText(":   45", 97F, 249F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 259F, textPaintPatient)
//        canvas.drawText(":   35", 97F, 269F, textPaintPatient)
//        canvas.drawText(":   45", 97F, 279F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 289F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 299F, textPaintPatient)
//        canvas.drawText(":   35", 97F, 309F, textPaintPatient)
//        canvas.drawText(":   45", 97F, 319F, textPaintPatient)
//        canvas.drawText(":   2.7", 97F, 329F, textPaintPatient)
//
//        //Units
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText("10^9/L", 130F, 239F, textPaintPatient)
//        canvas.drawText("%", 130F, 249F, textPaintPatient)
//        canvas.drawText("%", 130F, 259F, textPaintPatient)
//        canvas.drawText("%", 130F, 269F, textPaintPatient)
//        canvas.drawText("%", 130F, 279F, textPaintPatient)
//        canvas.drawText("%", 130F, 289F, textPaintPatient)
//        canvas.drawText("10^9/L", 130F, 299F, textPaintPatient)
//        canvas.drawText("10^9/L", 130F, 309F, textPaintPatient)
//        canvas.drawText("10^9/L", 130F, 319F, textPaintPatient)
//        canvas.drawText("10^9/L", 130F, 329F, textPaintPatient)
//
//        //Ref. Range
//        textPaintPatient.typeface = Typeface.create("Roboto", Typeface.NORMAL)
//        canvas.drawText("14-18", 168F, 239F, textPaintPatient)
//        canvas.drawText("45-54", 168F, 249F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 259F, textPaintPatient)
//        canvas.drawText("32.5-36", 168F, 269F, textPaintPatient)
//        canvas.drawText("42-54", 168F, 279F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 289F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 299F, textPaintPatient)
//        canvas.drawText("32.5-36", 168F, 309F, textPaintPatient)
//        canvas.drawText("42-54", 168F, 319F, textPaintPatient)
//        canvas.drawText("2.5-3.2", 168F, 329F, textPaintPatient)
//
//
//        //Footer1
//        paint.color = getColor(R.color.dark_blue)
//        canvas.drawRect(0F, 370F, 300F, 390F, paint)
//        val xPos1 = (canvas.width / 5).toFloat()
//        val textPaint1 = TextPaint()
//        val customTypeFace6 = ResourcesCompat.getFont(applicationContext, R.font.roboto_medium)
//        textPaint1.color = getColor(R.color.white)
//        textPaint1.textSize = 6F
//        textPaint1.typeface = customTypeFace6
//        canvas.drawText(
//            "+91 8508092626  |  support@mefy.care  |  CIN No. - 547474684656457",
//            xPos1,
//            383F,
//            textPaint1
//        )
//
//
//        //Footer2
//        paint.color = getColor(R.color.purple_700)
//        canvas.drawRect(0F, 390F, 300F, 400F, paint)
//        val xPos = (canvas.width / 4).toFloat()
//        val textPaint = TextPaint()
//        textPaint.color = getColor(R.color.white)
//        textPaint.textSize = 5.5F
//        textPaint.typeface = customTypeFace6
//        canvas.drawText(
//            "This Prescription is automatically generated by MeFy Care",
//            xPos,
//            397F,
//            textPaint
//        )
//
//
//        //Top Border of the Page
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 500F
//        paint.color = getColor(R.color.purple_700)
//        canvas.drawLine(10F, 0F, 10F, 10F, paint)
//
//        pdfDocument.finishPage(myPage1)
//
//        //Writing File to the External Storage
//        if (isExternalStorageWritable()) {
//            val file = File(Environment.getExternalStorageDirectory(), "/NewTest.pdf")
//            try {
//                pdfDocument.writeTo(FileOutputStream(file))
//                pdfDocument.close()
//                Toast.makeText(this, "File is successfully saved ${file.path}", Toast.LENGTH_SHORT)
//                    .show()
//            } catch (e: IOException) {
//                Toast.makeText(this, "File is not saved ${e.localizedMessage}", Toast.LENGTH_SHORT)
//                    .show()
//                e.printStackTrace()
//            }
//        } else {
//            Toast.makeText(this, "Can't save to external storage", Toast.LENGTH_SHORT).show()
//            pdfDocument.close()
//        }
//    }
//
//    fun isExternalStorageWritable(): Boolean {
//        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
//    }


}