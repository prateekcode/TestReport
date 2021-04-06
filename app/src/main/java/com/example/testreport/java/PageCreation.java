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
import com.example.testreport.freshcreation.WritingToStorage;
import com.example.testreport.model.Conditions;
import com.example.testreport.model.NewPatient;
import com.example.testreport.model.Parameters;

import org.jetbrains.annotations.NotNull;

public final class PageCreation {

    //    private PageWriteToStorage pageWriteToStorage;

    public final void conditionalPageCreation(@NotNull Context context, @NotNull NewPatient newPatient, @NotNull Bitmap bitmap) {
        PdfDocument createThePdfDocument = new PdfDocument();
        int currentPageNumber = 1;

        PageHelper pageHelper = new PageHelper();
//        pageWriteToStorage = new PageWriteToStorage();

        int sizeOfConditionList = newPatient.getSampleType().getConditionList().size();

        PdfDocument.Page page;
        Canvas canvas;

//  Start new page and assign page and canvas to Global variables.
        page = createThePdfDocument.startPage(this.pageInfoHelper(currentPageNumber++));
        canvas = page.getCanvas();

//  Print headers
        pageHelper.headerPaint(context, bitmap, canvas);
        pageHelper.patientDetail(context, canvas, newPatient);
        pageHelper.elementHeaderTable(context, canvas);

        TextPaint textPaint = new TextPaint();
        Paint paint = new Paint();

        float newValueOfY = 125.0F;

        for (int pageNo = 0; pageNo < sizeOfConditionList; pageNo++) {

            Conditions condition = newPatient.getSampleType().getConditionList().get(pageNo);

//  Paint Condition header // Complete Blood count.
            this.textPaintHelper(textPaint, 6.0F);
            paint.setColor(ContextCompat.getColor(context, R.color.custom_extra_light_blue));
            canvas.drawRect(10.0F, newValueOfY, 237.0F, newValueOfY + 10.0F, paint);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            canvas.drawText(condition.getConditionHeader(), (float) canvas.getWidth() / 2.0F, newValueOfY + 7.0F, (Paint) textPaint);

//  Paint Condition name  // Anemia
            newValueOfY += 16.5F;
            Typeface customTypeFace3 = ResourcesCompat.getFont(context, R.font.roboto_medium);
            textPaint.setTypeface(customTypeFace3);
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            paint.setColor(ContextCompat.getColor(context, R.color.light_grey));
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(condition.getConditionName(), (float) canvas.getWidth() / 2.0F, newValueOfY, (Paint) textPaint);

            int testParameterSize = condition.getTestParameters().size();

            for (int i = 0; i < testParameterSize; i++) {

                Log.d("ContentValues", "SIZE OF CONDITION LIST --> " + testParameterSize);

                Parameters parameter = condition.getTestParameters().get(i);

                if (newValueOfY > 330.0F) {
                    pageHelper.footerPaint(context, canvas);
                    createThePdfDocument.finishPage(page);
                    page = createThePdfDocument.startPage(this.pageInfoHelper(currentPageNumber++));
                    canvas = page.getCanvas();

                    pageHelper.headerPaint(context, bitmap, canvas);
                    pageHelper.patientDetail(context, canvas, newPatient);
                    pageHelper.elementHeaderTable(context, canvas);

                    newValueOfY = 125.0F;
                    this.textPaintHelper(textPaint, 6.0F);
                    paint.setColor(ContextCompat.getColor(context, R.color.custom_extra_light_blue));
                    canvas.drawRect(10.0F, newValueOfY, 237.0F, newValueOfY + 10.0F, paint);
                    textPaint.setTextAlign(Paint.Align.CENTER);
                    textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                    canvas.drawText(condition.getConditionHeader(), (float) canvas.getWidth() / 2.0F, newValueOfY + 7.0F, (Paint) textPaint);

//  Paint Condition name  // Anemia
                    newValueOfY += 16.5F;
                    Typeface customTypeFace4 = ResourcesCompat.getFont(context, R.font.roboto_medium);
                    textPaint.setTypeface(customTypeFace4);
                    textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                    paint.setColor(ContextCompat.getColor(context, R.color.light_grey));
                    textPaint.setColor(ContextCompat.getColor(context, R.color.black));
                    textPaint.setTextAlign(Paint.Align.CENTER);
                    canvas.drawText(condition.getConditionName(), (float) canvas.getWidth() / 2.0F, newValueOfY, (Paint) textPaint);

                    this.textPaintHelper(textPaint, 6.0F);
                    textPaint.setTextAlign(Paint.Align.CENTER);
                    Log.d("ContentValues", "The New Value of Y is " + newValueOfY);
                    Log.d("ContentValues", "conditionalPageCreation: HEIGHT INCREASED");
                }

                float yInParameter = newValueOfY + 5.0F + (float) (i * 10 + 10);

                textPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(parameter.getParameterName(), 20.0F, yInParameter, (Paint) textPaint);
                canvas.drawText(":   " + parameter.getParameterResponse().getResults(), 97.0F, yInParameter, (Paint) textPaint);
                canvas.drawText(parameter.getParameterResponse().getUnits(), 135.0F, yInParameter, (Paint) textPaint);
                canvas.drawText(parameter.getParameterResponse().getRef_range(), 173.0F, yInParameter, (Paint) textPaint);

                Log.e("Y value", " after print Parameter " + parameter.getParameterName() + " --- " + yInParameter);

            }

            newValueOfY = newValueOfY + 5.0F + (float) (testParameterSize * 10 + 10);
            Log.e("Y value", " before new  Parameter " + newValueOfY);
        }

        if (newValueOfY > 330.0F) {
            pageHelper.footerPaint(context, canvas);
            createThePdfDocument.finishPage(page);
            page = createThePdfDocument.startPage(this.pageInfoHelper(currentPageNumber));
            canvas = page.getCanvas();

            pageHelper.headerPaint(context, bitmap, canvas);
            pageHelper.patientDetail(context, canvas, newPatient);
        }
        pageHelper.pathologistDetail(context, canvas);
        pageHelper.footerPaint(context, canvas);
        createThePdfDocument.finishPage(page);
        WritingToStorage.INSTANCE.writingToExternalStorage(context, createThePdfDocument, newPatient);
    }


    private final void pageCreationConditional(Context context, NewPatient newPatient, Bitmap bitmap) {
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
        Log.e("PageNumber", String.valueOf(pageNumber));
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


