package com.example.metro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Userbooking extends AppCompatActivity implements JsonResponse, AdapterView.OnItemSelectedListener {
    SharedPreferences sh;
    Spinner s1;
    Button b1;
    EditText e1,e2,e5;
    String quantityneed,amount;

    String date,quan;
    Integer total;
    String[] mincharge,stch,value,rid;
    public static String rids,amounts;
    private DatePickerDialog fromDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userbooking);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        s1=(Spinner)findViewById(R.id.spinner);
        s1.setOnItemSelectedListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Userbooking.this;
        String q="/userviewrate";
        q=q.replace(" ","%20");
        JR.execute(q);
        e1=(EditText)findViewById(R.id.noofseats);
        e2=(EditText)findViewById(R.id.date);
        e5=(EditText)findViewById(R.id.ettotal);
        b1=(Button)findViewById(R.id.button);
        e2.setInputType(InputType.TYPE_NULL);
        e2.requestFocus();
        setDateTimeField();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(e1.getText().toString().equalsIgnoreCase(""))
                {

                }
                else if(e1.getText().toString().equalsIgnoreCase(".")||e1.getText().toString().equalsIgnoreCase("0"))
                {
                    e1.setText("");
                }
                else {
                    quan = e1.getText().toString();

                    total = Integer.parseInt(quan) * Integer.parseInt(Userbooking.amounts);
                    e5.setText(total + "");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantityneed=e1.getText().toString();
                date=e2.getText().toString();
                if(quantityneed.equalsIgnoreCase("")){
                    e1.setError("Enter seats");
                }
                else  if(date.equalsIgnoreCase("")){
                    e2.setError("enter date");
                }
                else{
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userbooking.this;
                    String q = "/userbooking?rid=" + Userbooking.rids + "&seat=" + quantityneed + "&amount=" + e5.getText().toString() + "&date=" + date + "&lid=" + sh.getString("log_id", "")+"&tid="+Userviewtrips.tids;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }
            }
        });
    }

    private void setDateTimeField() {
        e2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                fromDatePickerDialog.show();
            }
        });
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog =new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                e2.setText(dateFormatter.format(newDate.getTime()));
//                bdate=e2.getText().toString();

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void response(JSONObject jo) {

        try {

            String method = jo.getString("method");
            if (method.equalsIgnoreCase("userbooking")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "BOOKED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), Userhome.class));

                } else {

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
                }
            } else if (method.equalsIgnoreCase("userviewrate")) {
                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    //feedback_id=new String[ja1.length()];
                    rid = new String[ja1.length()];
                    // reply = new String[ja1.length()];
                    mincharge = new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        rid[i] = ja1.getJSONObject(i).getString("rate_id");
                        //   reply[i] = ja1.getJSONObject(i).getString("reply");
                        mincharge[i] = ja1.getJSONObject(i).getString("min_charge");
                        value[i] = "MinCharge: " + mincharge[i] ;

                    }
                    ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                    s1.setAdapter(ar);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rids=rid[position];
        amounts=mincharge[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}