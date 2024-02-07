package com.example.metro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Publicaddfeedback extends AppCompatActivity implements JsonResponse{

    EditText e1;
    String complaint;
    Button b1;
    ListView l1;
    String[] complaints,reply,date,value;
    SharedPreferences sh;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaddfeedback);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=(EditText)findViewById(R.id.etcomplaint);
        b1=(Button)findViewById(R.id.button);
        l1=(ListView)findViewById(R.id.lvview);

        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Publicaddfeedback.this;
        String q="/userviewfeedback?lid="+sh.getString("log_id","");
        q=q.replace(" ","%20");
        JR.execute(q);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaint=e1.getText().toString();
                if(complaint.equalsIgnoreCase("")|| !complaint.matches("[a-zA-Z ]+"))
                {
                    e1.setError("Enter your feedback");
                    e1.setFocusable(true);
                }

                else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Publicaddfeedback.this;
                    String q = "/useraddfeedback?feedback=" + complaint+"&lid="+sh.getString("log_id","");
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method = jo.getString("method");
            if (method.equalsIgnoreCase("useraddfeedback")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "SENDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Publicaddfeedback.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            } else if (method.equalsIgnoreCase("userviewfeedback")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    complaints = new String[ja1.length()];
                   // reply = new String[ja1.length()];
                    date = new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        complaints[i] = ja1.getJSONObject(i).getString("feedback");
                     //   reply[i] = ja1.getJSONObject(i).getString("reply");
                        date[i] = ja1.getJSONObject(i).getString("date_time");
                        value[i] = "Feedback: " + complaints[i]  + "\nDate: " + date[i];

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                    l1.setAdapter(ar);
                }
            }
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}