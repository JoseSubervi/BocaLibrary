package com.bocasystems.com.sample;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import bocasystems.com.sdk.BocaSystemsSDK;

import bocasystems.com.sdk.BocaSystemsSDK;

public class ScanActivity extends Activity {

    private TextView Status;
    private TextView FGL_Text;
    private Button Transmit;
    MyBocaSystemsSDK boca = null;
    private ArrayList<String> statusList = new ArrayList<String>();
    int STATUS_LIST_SIZE = 4;

    private class MyBocaSystemsSDK extends BocaSystemsSDK {
        @Override
        public void StatusReportCallback(String statusReport) {
            if (0 < statusReport.length())
            {
                AppendStatus(statusReport);
            }
        }
        @Override
        public long getMemorySizeInBytes()
        {
            Context context = getApplicationContext();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            long totalMemory = memoryInfo.totalMem;
            return totalMemory;
        }
    }

    public void AppendStatus(String msg) {
        if (0 == msg.trim().length())
        {
            return;
        }
        String[] status2 = msg.split("\n\r");
        String text;
        int i;
        for (i = 0; i < status2.length; i++) {
            if (0 < status2[i].trim().length())
            {
                statusList.add(status2[i]);
            }
        }
        while (STATUS_LIST_SIZE < statusList.size())
        {
            statusList.remove(0);
        }
        StringBuilder builder = new StringBuilder();
        for (String s: statusList)
        {
            builder.append(s);
            builder.append("\n");
        }
        text = builder.toString();
        Status.setText("");
        Status.setText(text.substring(0, text.lastIndexOf("\n")));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan);

        Transmit = findViewById(R.id.Transmit);
        Transmit.setEnabled(false);

        Transmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    //String url = "http://mticketapi3.preview.mmm.it/ticket/validate";
                    //ImageView bar_code = findViewById(R.id.bar_code);
                    CharSequence msg = "";
                    msg = FGL_Text.getText();
                    if (0 == msg.length())
                    {
                        msg = "<S1>";
                        FGL_Text.setText(msg);
                    }

                    //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    //        Request.Method.PUT,
                    //        url,


                    //);
                    boca.SendString(msg.toString());
                    // String status = boca.ReadPrinter();
                    // AppendStatus(status);
                } catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        //Button btnAccess = findViewById(R.id.Enter);

        //btnAccess.setOnClickListener(new View.OnClickListener(){
        //    @Override
        //    public void onClick(View v){
                //Intent enter = new Intent(InitActivity.this, MainActivity.class);
                //startActivity(enter);
        //    }
        //});

    }
}
