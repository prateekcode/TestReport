package com.example.testreport.java;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.widget.Toast;

import com.example.testreport.model.NewPatient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PageWriteToStorage {

    public PageWriteToStorage(){
    }

    private boolean isExternalStorageWritable(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    final void writeToExternalStorage(Context context, PdfDocument pdfDocument, NewPatient newPatient){
        if (this.isExternalStorageWritable()){
            String newPath = Environment.getExternalStorageState()
                    + "/" + "TestReport/";
            File directory = new File(newPath);
            if (!directory.exists()){
                directory.mkdirs();
            }
            File newFile = context.getFileStreamPath(newPatient.getPatientName()+newPatient.getGender()+".pdf");
            try {
                FileInputStream fileInputStream = new FileInputStream(newFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
//            File file = new File(Environment.getExternalStorageState(),
//                    newPatient.getPatientName()+newPatient.getGender() + ".pdf");


            try{
                pdfDocument.writeTo((OutputStream) new FileOutputStream(newFile));
                pdfDocument.close();
                Toast.makeText(context, "File is successfully saved to "+ newFile.getPath(), Toast.LENGTH_SHORT).show();
            }catch (IOException e){
                e.printStackTrace();
                e.getLocalizedMessage();
                Toast.makeText(context, "File is not able to save "+ e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(context, "Can't save to external storage", Toast.LENGTH_SHORT).show();
            pdfDocument.close();
        }
    }

}
