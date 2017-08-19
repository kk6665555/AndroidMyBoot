package com.example.mac.myonbootservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Notical extends AppCompatActivity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notical);
        tv=(TextView)findViewById(R.id.tv);
        tv.setText(""+getIntent().getIntExtra("key",-1));
    }
}
