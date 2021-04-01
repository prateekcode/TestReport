package com.example.testreport.freshcreation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import com.example.testreport.model.NewPatient

object CreateThePdf {

    fun createThePdf(
        newPatient: NewPatient,
        context: Context,
        bitmap: Bitmap
    ) {
        val createPdfDocument = PdfDocument()
        val pageInf = PdfDocument
            .PageInfo
            .Builder(250, 400, 1)
            .create()

        //Start of the page 1
        val page1 = createPdfDocument.startPage(pageInf) as PdfDocument.Page

        PaintPage.pagePainting(context, bitmap, newPatient, page1.canvas as Canvas)
        createPdfDocument.finishPage(page1)
        //End of the page 1

        //Writing File to the External Storage
        WritingToStorage.writingToExternalStorage(context, createPdfDocument, newPatient)

        //New page will be created on basis of last data
        //so it may be 2 page, 3 page, 4 page.. The data will be dynamic
        //so creation of the page must be dynamic as well

        //if the size of page 1 increased from 330f then it will come to
        //page 2 and if it crosses the value of height of page2 then it
        // goes to page3 and so on

        //What I need to do is I need the last height of the every page
        //content and using the conditional I can shift to different pages

        //Pseudo code
        //if(dataOfPage1>330f) then create page 2
        //if(dataOfPage2>330f) then create page 3
        //if(dataOfPage3>330f) then create page 4
        //and so on but question is till where?
        //see if the height of page1 crosses only then I need to create
        //next page until then I just need to paint on the same page
        //var globalVariableForPageCount = 1
        //if heightOfPage1 is more than 330f then in the global variable it
        //must increase the value by 1
        //globalVariableForPageCount++
        //val i = 1
        //while (i <= globalVariableForPageCount)
        //if(dataOfPage$i>330f) then create page i+1
        //i++

        //I have condition for page, now I just need to think about
        //how the element will be placed and how relative with other element
        //For example, if the conditionName is Anaemia and there no any other
        //element exist before it so need to check of the last element
        //its height from the top will be depend upon the last value
        //if the element inside the condition name is greater than the size
        //of 330f it must break the loop and store the last index of the loop
        //and return that last index so it will work same in the next page


        //Paint the single page and other will follow the same
        //What are the stuff the page will handle
        //1. Header Paint
        //2. Patient Detail
        //3. Header Table
        //4. TestTableContent
        //5. Pathologist Signature (only exists in the same page if height
        //is less than 270f of the last element)
        //6. Footer Paint
        //functionality of the pagePaint objects are:
        //a. Return the height used
        //b. Return the last index
        // parameter passed into pages are
        //context, newPatient, bitmap, lastValue, paint, canvas



    }

}