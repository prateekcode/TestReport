package com.example.testreport.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.text.TextPaint;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.testreport.R;
import com.example.testreport.freshcreation.PaintPage;
import com.example.testreport.freshcreation.WritingToStorage;
import com.example.testreport.model.Conditions;
import com.example.testreport.model.NewPatient;
import com.example.testreport.model.Parameters;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class PageCreation {

    private PageHelper pageHelper;
    private PageWriteToStorage pageWriteToStorage;

    public final void conditionalPageCreation(@NotNull Context context, @NotNull NewPatient newPatient, @NotNull Bitmap bitmap) {
        PdfDocument createThePdfDocument = new PdfDocument();
        float newValueOfY = 0.0F;
        int pageNumber = 0;
        int pageNo = 0;

        pageHelper = new PageHelper();
        pageWriteToStorage = new PageWriteToStorage();

        int sizeOfConditionList = newPatient.getSampleType().getConditionList().size();
        for(pageNo=0; pageNo<sizeOfConditionList; pageNo++) {

            PdfDocument.Page page = createThePdfDocument.startPage(this.pageInfoHelper(pageNo));
            Canvas canvas = page.getCanvas();
            pageHelper.headerPaint(context,bitmap, canvas);
            pageHelper.patientDetail(context, canvas, newPatient);
            pageHelper.elementHeaderTable(context, canvas);

            TextPaint textPaint = new TextPaint();
            Paint paint = new Paint();
            int i = 0;

            Canvas canvas1;

            for(i=0; i<sizeOfConditionList; i++) {

                Log.d("ContentValues", "SIZE OF CONDITION LIST --> " + newPatient.getSampleType().getConditionList().size());
                if (newValueOfY > 330.0F) {
                    createThePdfDocument.getPages().get(pageNumber++);
                    this.textPaintHelper(textPaint, 6.0F);
                    textPaint.setTextAlign(Paint.Align.CENTER);
                    Log.d("ContentValues", "The New Value of Y is " + newValueOfY);
                    PdfDocument.PageInfo pageInfo = page.getInfo();
                    pageInfo.getPageNumber();
                    canvas1 = page.getCanvas();
                    pageHelper.pathologistDetail(context, canvas1);
                    Log.d("ContentValues", "conditionalPageCreation: HEIGHT INCREASED");
                }

                newValueOfY = 125.0F;
                this.textPaintHelper(textPaint, 6.0F);
                paint.setColor(ContextCompat.getColor(context, R.color.custom_extra_light_blue));
                canvas.drawRect(10.0F, newValueOfY, 237.0F, newValueOfY + 10.0F, paint);
                textPaint.setTextAlign(Paint.Align.CENTER);
                textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                canvas.drawText(((Conditions)newPatient.getSampleType().getConditionList().get(i)).getCondtionHeader(), (float)canvas.getWidth() / 2.0F, newValueOfY + 7.0F, (Paint)textPaint);
                newValueOfY += 16.5F;
                Typeface customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium);
                textPaint.setTypeface(customTypeFace3);
                textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                paint.setColor(ContextCompat.getColor(context, R.color.light_grey));
                textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                textPaint.setTextAlign(Paint.Align.CENTER);
                Log.d("ContentValues", "CONDITION NAME of " + newPatient.getPatientName() + ": " + ((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionName());
                Log.d("ContentValues", "Checking the newvalueofY here " + newValueOfY);
                if (newValueOfY >= 330.0F) {
                    break;
                }

                newValueOfY *= (float)(i + 1);
                canvas.drawRect(10.0F, newValueOfY - 7.0F, 237.0F, newValueOfY + 3.0F, paint);
                canvas.drawText(((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionName(), (float)canvas.getWidth() / 2.0F, newValueOfY, (Paint)textPaint);
                int parameter = 0;

                for(int var17 = ((Collection)((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionTypeList()).size(); parameter < var17; ++parameter) {
                    textPaint.setTextAlign(Paint.Align.LEFT);
                    float yInParameter = newValueOfY + 5.0F + (float)(parameter * 10 + 10);
                    if (newValueOfY >= 330.0F || yInParameter >= 360.0F) {
                        break;
                    }

                    canvas.drawText(((Parameters)((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionTypeList().get(parameter)).getParameterName(), 20.0F, yInParameter, (Paint)textPaint);
                    canvas.drawText(":   " + ((Parameters)((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionTypeList().get(parameter)).getParameterResponse().getResults(), 97.0F, yInParameter, (Paint)textPaint);
                    canvas.drawText(((Parameters)((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionTypeList().get(parameter)).getParameterResponse().getUnits(), 135.0F, yInParameter, (Paint)textPaint);
                    canvas.drawText(((Parameters)((Conditions)newPatient.getSampleType().getConditionList().get(i)).getConditionTypeList().get(parameter)).getParameterResponse().getRef_range(), 173.0F, yInParameter, (Paint)textPaint);
                }
            }

            canvas1 = page.getCanvas();
            pageHelper.footerPaint(context, canvas1);
            createThePdfDocument.finishPage(page);
        }
        WritingToStorage.INSTANCE.writingToExternalStorage(context, createThePdfDocument, newPatient);
    }



    private final void pageCreationConditional(Context context, NewPatient newPatient, Bitmap bitmap){
        PdfDocument createThePdfDocument = new PdfDocument();
        float newValueOfY = 0.0F;
        int pageNumber = 0;
        int pageNo = 0;
    }

    private final float elementTableContent(@NotNull Context context, @NotNull Canvas canvas, @NotNull NewPatient newPatient) {
        new TextPaint();
        float downYStartPoint = 125.0F;
        downYStartPoint = 340.0F;
        return downYStartPoint;
    }


    private PdfDocument.PageInfo pageInfoHelper(int pageNumber) {
        return (new PdfDocument.PageInfo.Builder(250, 400, pageNumber)).create();
    }

    private TextPaint textPaintHelper(TextPaint textPaint, float textSize) {
        textPaint.setTextSize(textSize);
        textPaint.setLetterSpacing(0.001F);
        textPaint.setAntiAlias(true);
        textPaint.setLinearText(true);
        textPaint.setSubpixelText(true);
        return textPaint;
    }

}


