package com.example.gregg.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jessicamay on 10/22/17.
 */

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        System.out.println("is it here?");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button register = (Button) findViewById(R.id.register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPw = password.getText().toString();
                    try {
                        if(JDBCInterface.getPassword(userEmail)==null) {
                            JDBCInterface.addUser(userEmail, userPw);
                            System.out.println("user added");
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else if(!email.contains("unl.edu"){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegisterActivity.this);
                            builder1.setMessage("ERROR: Not a .unl.edu email.")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                    }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("ERROR: Account with that username already exists.")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
        });
    }
}
