package com.example.testreport.freshcreation

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import com.example.testreport.model.NewPatient
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object WritingToStorage {

    fun writingToExternalStorage(
        context: Context,
        pdfDocument: PdfDocument,
        newPatient: NewPatient
    ){
        if (isExternalStorageWritable()) {
            val path = Environment.getExternalStorageDirectory().toString() + "/" + "TestReport/"
            val directory = File(path)
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val file = File(Environment.getExternalStorageDirectory(), "/TestReport/${newPatient.patientName + newPatient.gender}.pdf")
            //val fileOutputStream: FileOutputStream

            try {
                //fileOutputStream = context.openFileOutput("NewTest.pdf", MODE_PRIVATE)
                //fileOutputStream.write(pdfDocument.pages)
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

    private fun isExternalStorageWritable(): Boolean {
        return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
    }

}