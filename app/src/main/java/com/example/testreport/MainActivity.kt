package com.example.testreport

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testreport.helper.ImageResizer
import com.example.testreport.helper.PdfReader
import com.example.testreport.helper.PermissionHelper
import com.example.testreport.java.PageCreation
import com.example.testreport.model.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    
    private var isGenerating = false
    private lateinit var bitmap: Bitmap
    private lateinit var scaledBitmap: Bitmap
    internal var myExternalFile: File? = null
    var newPatientDetail = listOf<NewPatient>()
    private val pageCreation = PageCreation()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gpath: String = Environment.getExternalStorageDirectory().absolutePath
        Log.w("gpath", "" + gpath)
        val spath = ""
        val fullpath = File(gpath + File.separator + spath)
        Log.w("fullpath", "" + fullpath)
        PdfReader.pdfReaderNew(fullpath)
        val lastPdf = PdfReader.pdfReaderNew(fullpath)
        for (i in lastPdf) {
            Log.d("lastpdf", "Checking about $i")
            fileNameTextView.text = i.name
        }


        val options = BitmapFactory.Options()
        //options.inDither = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888

        //bitmap = imageResizer.decodeSampledBitmapFromResource(resources, R.drawable.logo, 20, 8)

        //bitmap = BitmapFactory.decodeResource(resources, R.drawable.logo, options)

//        val imageHeight: Int = options.outHeight
//        val imageWidth: Int = options.outWidth
//        val imageType: String = options.outMimeType

        bitmap = ImageResizer.getBitmapFromVectorDrawable(applicationContext, R.drawable.logo)!!
        scaledBitmap = Bitmap.createScaledBitmap(bitmap, 70, 20, false)
        //scaledBitmap = pdfCreator.BITMAP_RESIZER(bitmap, 200, 18)!!
        newPatientDetails()

        permissionCheck(false)
        buttonSavePdf.setOnClickListener {
            permissionCheck(true)
            for (new in newPatientDetail) {

                pageCreation.conditionalPageCreation(
                    applicationContext,
                    new,
                    bitmap
                )

            }
        }
    }

    private fun newPatientDetails() {
        newPatientDetail = listOf(
            NewPatient(
                "HappyPerson",
                22,
                "M",
                "Dr. Qwerty",
                "1452",
                "18/03/2021",
                "22/03/2021",
                SampleType(
                    "EDTA_blood",
                    "Blood Test Report",
                    conditionList = listOf(
                        Conditions(
                            "Anaemia",
                            listOf(
                                Parameters(
                                    "Hgb Haemoglobin",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "Hct Hematocrit",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "Hct/Hgb (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),



                                )
                        ),
                        Conditions(
                            "Complete Blood Count",
                            listOf(
                                Parameters(
                                    "White Blood count",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Neutrophil percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Lymphocyte percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Monocyte percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "White Blood count",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Neutrophil percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Lymphocyte percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Monocyte percent",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "MCHC (Calculated)",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "Anidnidn",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "fweihfio",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "fowjjf",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "fwepofpe",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),
                                Parameters(
                                    "orjjeofr",
                                    ParameterResponse(
                                        "12.7",
                                        "12",
                                        "14-18",
                                        2
                                    )
                                ),

                                )

                        ),
                        Conditions(
                            "Hormone Check",
                            listOf(
                                Parameters(
                                    "Harmone 1",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                                Parameters(
                                    "Harmone 2",
                                    ParameterResponse(
                                        "3.7",
                                        "10^9/L",
                                        "3.7-9.3",
                                        1
                                    )
                                ),
                            )
                        )
                    )
                )
            )
        )
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
}