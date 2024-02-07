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
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class Userviewtrips extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    ListView l1;
    String[] starttime,endtime,towards,value,tid,fsid,tsid;
    public static String tids,fsids,tsids;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewtrips);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Userviewtrips.this;
        String q="/userviewtrips?tid="+userviewtrain.sids;
        q=q.replace(" ","%20");
        JR.execute(q);
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

            if(method.equalsIgnoreCase("userviewtrips")) {


                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    tid = new String[ja1.length()];
                    fsid = new String[ja1.length()];
                    tsid = new String[ja1.length()];
                    starttime = new String[ja1.length()];
                    endtime = new String[ja1.length()];
                    towards = new String[ja1.length()];
//                    statuss = new String[ja1.length()];
//                    date = new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        tid[i] = ja1.getJSONObject(i).getString("trip_id");
                        fsid[i] = ja1.getJSONObject(i).getString("from_station_id");
                        tsid[i] = ja1.getJSONObject(i).getString("to_station_id");
                        starttime[i] = ja1.getJSONObject(i).getString("start_time");
                        endtime[i] = ja1.getJSONObject(i).getString("end_time");
                        towards[i] = ja1.getJSONObject(i).getString("towards");
//                        lastdate[i] = ja1.getJSONObject(i).getString("last_date");
//                        statuss[i] = ja1.getJSONObject(i).getString("status");

                        value[i] = "Start Time: " + starttime[i] + "\nEndtime: " + endtime[i] + "\nTowards: " + towards[i];

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tids=tid[position];

        final CharSequence[] items = {"Book","Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Userviewtrips.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Book")) {

                  startActivity(new Intent(getApplicationContext(),Userbooking.class));

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