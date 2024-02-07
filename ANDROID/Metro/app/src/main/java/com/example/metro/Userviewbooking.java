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

public class Userviewbooking extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {
    SharedPreferences sh;
    ListView l1;
    String[] trainname,comp,bid,starttime,endtime,towards,seats,total,date,value,statuss;
    public static String bids,amounts,stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userviewbooking);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1=(ListView)findViewById(R.id.lvview);
        l1.setOnItemClickListener(this);
        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse)Userviewbooking.this;
        String q="/userviewbooking?lid="+sh.getString("log_id","");
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

            if(method.equalsIgnoreCase("userviewbooking")) {


                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                    bid = new String[ja1.length()];
                    trainname = new String[ja1.length()];
                    comp = new String[ja1.length()];
                    starttime = new String[ja1.length()];
                    endtime = new String[ja1.length()];
                    towards = new String[ja1.length()];
                    statuss = new String[ja1.length()];
                    date = new String[ja1.length()];
                    total = new String[ja1.length()];
                    seats = new String[ja1.length()];
                    value = new String[ja1.length()];

                    for (int i = 0; i < ja1.length(); i++) {
                        bid[i] = ja1.getJSONObject(i).getString("booking_id");
                        trainname[i] = ja1.getJSONObject(i).getString("train_name");
                        comp[i] = ja1.getJSONObject(i).getString("compartments");
                        starttime[i] = ja1.getJSONObject(i).getString("start_time");
                        endtime[i] = ja1.getJSONObject(i).getString("end_time");
                        towards[i] = ja1.getJSONObject(i).getString("towards");
                        date[i] = ja1.getJSONObject(i).getString("date");
                        seats[i] = ja1.getJSONObject(i).getString("no_seats");
                        total[i] = ja1.getJSONObject(i).getString("total");
                        statuss[i] = ja1.getJSONObject(i).getString("status");

                        value[i] = "Train Name: " + trainname[i] + "\nCompartments: " + comp[i] +"\nStart Time: " + starttime[i] + "\nEndtime: " + endtime[i] + "\nTowards: " + towards[i]+"\nSeats: " + seats[i] + "\nTotal: " + total[i] +"\nDate: " + date[i]+"\nStatus: " + statuss[i];

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
        bids=bid[position];
        amounts=total[position];
        stat=statuss[position];
        if(stat.equalsIgnoreCase("pending")){
            final CharSequence[] items = {"payment","Cancel"};

            AlertDialog.Builder builder = new AlertDialog.Builder(Userviewbooking.this);
            // builder.setTitle("Add Photo!");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {

                    if (items[item].equals("payment")) {

                        startActivity(new Intent(getApplicationContext(),Usermakepayment.class));

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
}