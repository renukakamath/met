package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Registration extends AppCompatActivity  implements JsonResponse {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button b1;
    String fname,lname,email,address,phone,place,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=(EditText)findViewById(R.id.textView4);
        e2=(EditText)findViewById(R.id.editTextview5);
        e3=(EditText)findViewById(R.id.editTextview6);
        e4=(EditText)findViewById(R.id.address);
        e5=(EditText)findViewById(R.id.phone);
        e6=(EditText)findViewById(R.id.editTextview9);
        e7=(EditText)findViewById(R.id.username);
        e8=(EditText)findViewById(R.id.password);

        b1=(Button)findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                email=e3.getText().toString();
                phone=e4.getText().toString();
                address=e5.getText().toString();
                place=e6.getText().toString();
                username=e7.getText().toString();
                password=e8.getText().toString();
                if(fname.equalsIgnoreCase(""))
                {
                    e1.setError("enter your first name");
                    e1.setFocusable(true);
                }
                else if(lname.equalsIgnoreCase(""))
                {
                    e2.setError("enter your lastname");
                    e2.setFocusable(true);
                }
                else if(email.equalsIgnoreCase(""))
                {
                    e3.setError("enter your email");
                    e3.setFocusable(true);
                }
                else if(phone.equalsIgnoreCase(""))
                {
                    e4.setError("enter your phone number");
                    e4.setFocusable(true);
                }
                 else if(address.equalsIgnoreCase(""))
                {
                    e5.setError("enter your  address");
                    e5.setFocusable(true);
                }
                  else if(place.equalsIgnoreCase(""))
                {
                    e6.setError("enter your place");
                    e6.setFocusable(true);
                }
                else if(username.equalsIgnoreCase(""))
                {
                    e7.setError("enter your username");
                    e7.setFocusable(true);
                }
                else if(password.equalsIgnoreCase(""))
                {
                    e8.setError("enter your password");
                    e8.setFocusable(true);
                }

                else
                {
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)Registration.this;
                    String q="/Registration?firstname=" + fname + "&lastname=" + lname + "&email=" + email + "&address=" + address + "&phonenumber=" + phone + "&place=" + place + "&username=" + username + "password+=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }


            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status=jo.getString("status");
            Log.d("pearl",status);


            if(status.equalsIgnoreCase("success")){





                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
            else if(status.equalsIgnoreCase("duplicate")){


                startActivity(new Intent(getApplicationContext(),Login.class));
                Toast.makeText(getApplicationContext(), "Username already Exist...", Toast.LENGTH_LONG).show();

            }
            else
            {
                startActivity(new Intent(getApplicationContext(),Registration.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}