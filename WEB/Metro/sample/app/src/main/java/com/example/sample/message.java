package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

public class message<j> extends AppCompatActivity  implements JsonResponse{
    EditText e1;
    Button b1;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        e1=(EditText)findViewById(R.id.message);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = e1.getText().toString();
                if (message.equalsIgnoreCase("")) {
                    e1.setError("enter your message");
                    e1.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) message.this;
                    String q = "/message?message+=" + message;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }
            }
        });


    }

    @Override
    public void response(JSONObject jo) {

    }
}