package com.example.getnews.HelperClasses;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util extends AppCompatActivity {



    public  String parsedate(String date){
        if(date == null)
            return "Unknown";
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy MM dd");
        Date dateformatter = null;
        try {
            dateformatter = inputFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputFormatter.format(dateformatter);

    }


    public  String  NullcheckforResponse(String response)
    {
        if (response == null)
        {
            return "Unknown";
        }
        else
        {
            return response;
        }
    }
}
