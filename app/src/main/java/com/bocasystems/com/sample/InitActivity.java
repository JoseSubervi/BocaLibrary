package com.bocasystems.com.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.*;
import it.custom.printer.api.android.*;
import it.custom.printer.api.android.CustomPrinter;
public class InitActivity extends Activity {

    private CustomAndroidAPI prnDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init);
        
        ImageView IconGroup = findViewById(R.id.IconSettings);
        IconGroup.setColorFilter(getResources().getColor(R.color.colorSettings));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

        IconGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent enter = new Intent(InitActivity.this, MainActivity.class);
                startActivity(enter);
            }
        });

    }

}
