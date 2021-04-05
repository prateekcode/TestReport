package com.example.testreport.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.example.testreport.R;
import com.example.testreport.helper.PdfCreator;
import com.example.testreport.model.NewPatient;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public class PageHelper {

    public final boolean newPagePainting(@NotNull Context context, @NotNull Bitmap bitmap, @NotNull NewPatient newPatient, @NotNull Canvas canvas) {
        this.headerPaint(context, bitmap, canvas);
        this.patientDetail(context, canvas, newPatient);
        this.elementHeaderTable(context, canvas);
        this.elementTableContent(context, canvas, newPatient);
        this.pathologistDetail(context, canvas);
        this.footerPaint(context, canvas);
        return this.elementTableContent(context, canvas, newPatient) > 330.0F;
    }

    public final void headerPaint(@NotNull Context context, @NotNull Bitmap bitmap, @NotNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.custom_blue));
        Typeface customTypeFace = ResourcesCompat.getFont(context, R.font.roboto_black);
        paint.setTypeface(customTypeFace);
        paint.setTextSize(14.0F);
        canvas.drawText("Mars Mission Alien Clinic", 80.0F, 25.0F, paint);
        Typeface customTypeFace2 = ResourcesCompat.getFont(context, R.font.roboto_medium);
        paint.setTypeface(customTypeFace2);
        paint.setLetterSpacing(0.001F);
        paint.setAntiAlias(true);
        paint.setLinearText(true);
        paint.setSubpixelText(true);
        paint.setColor(ContextCompat.getColor(context, R.color.black));
        paint.setTextSize(5.0F);
        canvas.drawText("ABC Place not Exist in this world", 80.0F, 35.0F, paint);
        canvas.drawLine(194.0F, 30.0F, 194.0F, 42.0F, paint);
        paint.setTypeface(customTypeFace2);
        paint.setTextSize(5.0F);
        canvas.drawText("xxxxxxxxxx", 198.0F, 35.0F, paint);
        canvas.drawText("xxxxxxxxxx", 198.0F, 42.0F, paint);
        paint.setColor(ContextCompat.getColor(context, R.color.black));
        paint.setTextSize(5.0F);
        canvas.drawText("Mars", 80.0F, 40.0F, paint);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        Bitmap bitmap1 = PdfCreator.INSTANCE.BITMAP_RESIZER(bitmap, 65, 20);
        canvas.drawBitmap(bitmap1, 5.0F, 20.0F, paint);
    }

    public final void patientDetail(@NotNull Context context, @NotNull Canvas canvas, @NotNull NewPatient newPatient) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.custom_blue));
        canvas.drawRect(0.0F, 50.0F, 300.0F, 110.0F, paint);
        TextPaint textPaintPatient = new TextPaint();
        Typeface customTypeFace4 = ResourcesCompat.getFont(context, R.font.roboto_bold);
        textPaintPatient.setTypeface(customTypeFace4);
        textPaintPatient.setLetterSpacing(0.001F);
        textPaintPatient.setAntiAlias(true);
        textPaintPatient.setLinearText(true);
        textPaintPatient.setSubpixelText(true);
        textPaintPatient.setColor(ContextCompat.getColor(context, R.color.white));
        textPaintPatient.setTextSize(6.0F);
        canvas.drawText("Patient Name   :", 10.0F, 65.0F, (Paint) textPaintPatient);
        canvas.drawText("Age & Gender   :", 10.0F, 75.0F, (Paint) textPaintPatient);
        canvas.drawText("Referred by      :", 10.0F, 85.0F, (Paint) textPaintPatient);
        canvas.drawText("Sample Type    :", 10.0F, 95.0F, (Paint) textPaintPatient);
        Typeface customTypeFace5 = ResourcesCompat.getFont(context, R.font.roboto);
        textPaintPatient.setTypeface(customTypeFace5);
        canvas.drawText(newPatient.getPatientName(), 60.0F, 65.0F, (Paint) textPaintPatient);
        canvas.drawText(newPatient.getAge() + " Year/" + newPatient.getGender(), 60.0F, 75.0F, (Paint) textPaintPatient);
        canvas.drawText(newPatient.getReferredDoctor(), 60.0F, 85.0F, (Paint) textPaintPatient);
        canvas.drawText(newPatient.getSampleType().getSampleTypeName(), 60.0F, 95.0F, (Paint) textPaintPatient);
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        float midX = (float) (canvas.getWidth() / 2);
        canvas.drawLine(midX, 50.0F, midX, 110.0F, paint);
        textPaintPatient.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
        canvas.drawText("Authorization Date   :", midX + 4.0F, 65.0F, (Paint) textPaintPatient);
        canvas.drawText("Report Date   :", midX + 4.0F, 75.0F, (Paint) textPaintPatient);
        canvas.drawText("Patient ID      :", midX + 4.0F, 85.0F, (Paint) textPaintPatient);
        textPaintPatient.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
        canvas.drawText(newPatient.getAuthorizationDate(), midX + (float) 62, 65.0F, (Paint) textPaintPatient);
        canvas.drawText(newPatient.getReportDate(), midX + (float) 62, 75.0F, (Paint) textPaintPatient);
        canvas.drawText(newPatient.getPatientId(), midX + (float) 62, 85.0F, (Paint) textPaintPatient);
    }

    public final void elementHeaderTable(@NotNull Context context, @NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.custom_light_blue));
        TextPaint textPaintPatient = new TextPaint();
        textPaintPatient.setTextSize(6.0F);
        textPaintPatient.setColor(ContextCompat.getColor(context, R.color.white));
        canvas.drawRect(10.0F, 115.0F, 80.0F, 125.0F, paint);
        textPaintPatient.setTypeface(Typeface.create("Roboto", Typeface.BOLD));
        canvas.drawText("Parameter", 20.0F, 122.0F, (Paint) textPaintPatient);
        canvas.drawRect(83.0F, 115.0F, 123.0F, 125.0F, paint);
        canvas.drawText("Results", 88.0F, 122.0F, (Paint) textPaintPatient);
        canvas.drawRect(126.0F, 115.0F, 162.0F, 125.0F, paint);
        canvas.drawText("Units", 130.0F, 122.0F, (Paint) textPaintPatient);
        canvas.drawRect(165.0F, 115.0F, 202.0F, 125.0F, paint);
        canvas.drawText("Ref. Range", 168.0F, 122.0F, (Paint) textPaintPatient);
        canvas.drawRect(205.0F, 115.0F, 237.0F, 125.0F, paint);
        canvas.drawText("Barcode", 209.0F, 122.0F, (Paint) textPaintPatient);
    }

    private final float elementTableContent(Context context, Canvas canvas, NewPatient newPatient) {
        new TextPaint();
        float downYStartPoint = 125.0F;
        downYStartPoint = 340.0F;
        return downYStartPoint;
    }

    public final void pathologistDetail(@NotNull Context context, @NotNull Canvas canvas) {
        TextPaint textPaintPatient = new TextPaint();
        Typeface customTypeFace7 = ResourcesCompat.getFont(context, R.font.roboto_medium);
        textPaintPatient.setTypeface(customTypeFace7);
        textPaintPatient.setColor(ContextCompat.getColor(context, R.color.custom_blue));
        textPaintPatient.setLetterSpacing(0.001F);
        textPaintPatient.setAntiAlias(true);
        textPaintPatient.setLinearText(true);
        textPaintPatient.setSubpixelText(true);
        textPaintPatient.setTextSize(6.0F);
        textPaintPatient.setColor(ContextCompat.getColor(context, R.color.black));
        canvas.drawText("Pathologist Signature", 170.0F, 330.0F, (Paint) textPaintPatient);
        Typeface customTypeFace8 = ResourcesCompat.getFont(context, R.font.roboto);
        textPaintPatient.setTypeface(customTypeFace8);
        textPaintPatient.setTextSize(4.0F);
        canvas.drawText("Dr. Ganesh Hansram", 170.0F, 350.0F, (Paint) textPaintPatient);
        canvas.drawText("Radiologist  |  MD", 170.0F, 355.0F, (Paint) textPaintPatient);
        canvas.drawText("3542656", 190.0F, 360.0F, (Paint) textPaintPatient);
        textPaintPatient.setTypeface(customTypeFace7);
        canvas.drawText("Reg. No.", 170.0F, 360.0F, (Paint) textPaintPatient);
    }

    public final void footerPaint(@NotNull Context context, @NotNull Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.custom_light_blue));
        canvas.drawRect(0.0F, 370.0F, 300.0F, 390.0F, paint);
        float xPos1 = (float) (canvas.getWidth() / 8);
        TextPaint textPaint1 = new TextPaint();
        Typeface customTypeFace6 = ResourcesCompat.getFont(context, R.font.roboto_medium);
        textPaint1.setColor(ContextCompat.getColor(context, R.color.white));
        textPaint1.setTextSize(6.0F);
        textPaint1.setTypeface(customTypeFace6);
//        textPaint1.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("+91 8508092626  |  support@mefy.care  |  CIN No. - 547474684656457", xPos1, 383.0F, (Paint) textPaint1);
        paint.setColor(ContextCompat.getColor(context, R.color.custom_blue));
        canvas.drawRect(0.0F, 390.0F, 300.0F, 400.0F, paint);

        float xPos = (float) (canvas.getWidth() / 5);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(ContextCompat.getColor(context, R.color.white));
        textPaint.setTextSize(5.5F);
        textPaint.setLetterSpacing(0.001F);
        textPaint.setAntiAlias(true);
        textPaint.setLinearText(true);
        textPaint.setSubpixelText(true);
        textPaint.setTypeface(customTypeFace6);
        canvas.drawText("This Prescription is automatically generated by MeFy Care", xPos, 397.0F, (Paint) textPaint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(500.0F);
        paint.setColor(ContextCompat.getColor(context, R.color.custom_blue));
        canvas.drawLine(10.0F, 0.0F, 10.0F, 10.0F, paint);
    }
}
