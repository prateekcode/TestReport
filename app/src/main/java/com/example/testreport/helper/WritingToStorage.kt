package com.example.testreport.helper

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.testreport.BuildConfig
import com.example.testreport.model.NewPatient
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object WritingToStorage {

    fun writingToExternalStorage(
        context: Context,
        pdfDocument: PdfDocument,
        newPatient: NewPatient
    ) {
        if (isExternalStorageWritable()) {

            val path = context.getExternalFilesDir(null).toString() + "/" + "TestReport/"
            val directory = File(path)
            if (!directory.exists()) {
                directory.mkdirs()
            }
            val file = File(
                context.getExternalFilesDir(null),
                "/TestReport/${newPatient.patientName + newPatient.gender}.pdf"
            )
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

                val uri = FileProvider.getUriForFile(
                    context.applicationContext,
                    "${BuildConfig.APPLICATION_ID}.provider",
                    file
                )

                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(uri)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
                context.startActivity(intent)
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