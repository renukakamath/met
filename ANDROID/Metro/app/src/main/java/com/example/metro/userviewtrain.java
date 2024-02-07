package com.example.metro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class userviewtrain extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    String[] name,description,scholarshipfor,eligibility,date,lastdate,statuss,value,sid;
    public  static  String sids;
    EditText e1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewtrain);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)userviewtrain.this;
        String q="/userviewtrain";
        q=q.replace(" ","%20");
        JR.execute(q);
        e1=(EditText)findViewById(R.id.etsearch);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String var=e1.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse)userviewtrain.this;
                String q="/usersearch?search="+var;
                q=q.replace(" ","%20");
                JR.execute(q);


            }
        });

    }

    @Override
    public void response(JSONObject jo) {

        try {
            String method=jo.getString("method");
//            if(method.equalsIgnoreCase("studentapplymatching")) {
//
//                String status = jo.getString("status");
//                Log.d("pearl", status);
//
//
//                if (status.equalsIgnoreCase("success")) {
//                    Toast.makeText(getApplicationContext(), "APPLIED SUCCESSFULLY", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(getApplicationContext(), userviewtrain.class));
//
//                } else {
//
//                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
//                }
//            }

             if(method.equalsIgnoreCase("userviewtrain")) {


                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    sid = new String[ja1.length()];
                    name = new String[ja1.length()];
                    description = new String[ja1.length()];
//                    scholarshipfor = new String[ja1.length()];
//                    eligibility = new String[ja1.length()];
//                    lastdate = new String[ja1.length()];
//                    statuss = new String[ja1.length()];
//                    date = new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        sid[i] = ja1.getJSONObject(i).getString("train_id");
                        name[i] = ja1.getJSONObject(i).getString("train_name");
                        description[i] = ja1.getJSONObject(i).getString("compartments");
//                        description[i] = ja1.getJSONObject(i).getString("description");
//                        scholarshipfor[i] = ja1.getJSONObject(i).getString("scholarship_for");
//                        eligibility[i] = ja1.getJSONObject(i).getString("eligibility");
//                        lastdate[i] = ja1.getJSONObject(i).getString("last_date");
//                        statuss[i] = ja1.getJSONObject(i).getString("status");

                        value[i] = "Train Name: " + name[i] + "\nCompartments: " + description[i];

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        sids=sid[position];
        startActivity(new Intent(getApplicationContext(),Userviewtrips.class));
//        final CharSequence[] items = {"Apply","Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(studentviewmatchingscholarship.this);
//        // builder.setTitle("Add Photo!");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//
//                     if (items[item].equals("Apply")) {
//
//
//                    JsonReq JR=new JsonReq();
//                    JR.json_response=(JsonResponse) studentviewmatchingscholarship.this;
//                    String q="/studentapplymatching?lid="+sh.getString("log_id","")+"&sid="+studentviewmatchingscholarship.sids;
//                    q=q.replace(" ","%20");
//                    JR.execute(q);
//
//                }
//
//                else if (items[item].equals("Cancel")) {
//                    dialog.dismiss();
//                }
//
//            }
//
//        });
//        builder.show();
    }
    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Userhome.class);
        startActivity(b);
    }
}