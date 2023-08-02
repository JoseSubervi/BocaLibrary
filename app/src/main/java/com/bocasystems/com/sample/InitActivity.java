package com.bocasystems.com.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.*;


public class InitActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_init);

        ImageView IconGroup = findViewById(R.id.IconSettings);
        IconGroup.setColorFilter(getResources().getColor(R.color.colorSettings));

        IconGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent enter = new Intent(InitActivity.this, MainActivity.class);
                startActivity(enter);
            }
        });

    }

}
