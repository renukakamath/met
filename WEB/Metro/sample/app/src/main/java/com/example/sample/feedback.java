package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class feedback extends AppCompatActivity  implements JsonResponse{
    EditText e1;
    Button b1;
    ListView l1;
    String feedback;
    public static String[] feedback_id,user_id,feedbacks,date,value;
    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        e1=(EditText)findViewById(R.id.View2);
        l1=(ListView)findViewById(R.id.listview);
        b1=(Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback = e1.getText().toString();
                if (feedback.equalsIgnoreCase("")) {
                    e1.setError("enter your feedback");
                    e1.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) feedback.this;
                    String q = "/feedback?feedback+=" + feedback;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }
            }
        });

    }

    @Override
    public void response(JSONObject jo) {
        try{
            String method=jo.getString("method");
            if(method.equalsIgnoreCase("usersendfeedback")){
                String status=jo.getString("status");
                Log.d("pearl",status);
                //Toast.makeText(getApplicationContext(),status, Toast.LENGTH_SHORT).show();
                if(status.equalsIgnoreCase("success")){

                    Toast.makeText(getApplicationContext(), " SENT", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),feedback.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Something went wrong!Try Again.", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Userhome.class));
                }
            }
            if(method.equalsIgnoreCase("userviewfeedback")){
                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    feedbacks=new String[ja1.length()];
                    date=new String[ja1.length()];
                    value=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        //feedback_id[i]=ja1.getJSONObject(i).getString("feedback_id");
                        feedbacks[i]=ja1.getJSONObject(i).getString("feedback");
                        date[i]=ja1.getJSONObject(i).getString("date_time");
                        value[i]="Feedback:  "+feedbacks[i]+"\nDate:  "+date[i];


                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,value);
                    l1.setAdapter(ar);
                    //startActivity(new Intent(getApplicationContext(),User_Post_Disease.class));
                }

                else

                {
                    Toast.makeText(getApplicationContext(), "No feedbacks!!", Toast.LENGTH_LONG).show();

                }
            }

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}