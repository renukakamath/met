package com.example.metro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Userviewmaster extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    String[] name,place,phone,email,lid,value,stationname;
    SharedPreferences sh;
    public static String  lids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewmaster);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Userviewmaster.this;
        String q="/userviewmaster";
        q=q.replace(" ","%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {

        try {


            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                lid = new String[ja1.length()];
                name = new String[ja1.length()];
                place = new String[ja1.length()];
                phone = new String[ja1.length()];
                email = new String[ja1.length()];
                stationname = new String[ja1.length()];
//                    statuss = new String[ja1.length()];
//                    date = new String[ja1.length()];
                value = new String[ja1.length()];

                for (int i = 0; i < ja1.length(); i++) {
                    lid[i] = ja1.getJSONObject(i).getString("login_id");
                    name[i] = ja1.getJSONObject(i).getString("first_name")+" "+ja1.getJSONObject(i).getString("last_name");
                    place[i] = ja1.getJSONObject(i).getString("splace");
                    phone[i] = ja1.getJSONObject(i).getString("phone");
                    email[i] = ja1.getJSONObject(i).getString("email");
                    stationname[i] = ja1.getJSONObject(i).getString("station_name");
//                        lastdate[i] = ja1.getJSONObject(i).getString("last_date");
//                        statuss[i] = ja1.getJSONObject(i).getString("status");

                    value[i] = "Name: " + name[i] + "\nPlace: " + place[i] + "\nPhone: " + phone[i]+ "\nEmail: " + email[i] + "\nStationNmae: " + stationname[i];

                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                l1.setAdapter(ar);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        lids=lid[position];
        SharedPreferences.Editor e=sh.edit();
        e.putString("logid",lids);
        e.putString("type","master");
        e.commit();
        final CharSequence[] items = {"Message","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Userviewmaster.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Message")) {

                    startActivity(new Intent(getApplicationContext(),Usersendmessage.class));

                }
//                else if (items[item].equals("View Booking")) {
//                    startActivity(new Intent(getApplicationContext(),Userviewbooking.class));
//
//                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();
    }
}