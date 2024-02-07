package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class booking extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    public static String seats,date,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        e1=(EditText)findViewById(R.id.seat);
        e2=(EditText)findViewById(R.id.date);
        e3=(EditText)findViewById(R.id.status);
        b1=(Button)findViewById(R.id.button);
    }
}