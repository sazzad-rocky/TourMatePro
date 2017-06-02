package com.example.afiqur.tourmatepro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.afiqur.tourmatepro.TravelEvent.TravelActivity;

import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    TextView doSignup;
    Button doLogin;

    private ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;
    public static final String LOGINPREF = "Login";
    public static final String USER_EMAIL = "userEmail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.enterusername);
        password = (EditText) findViewById(R.id.enterpassword);
        doSignup = (TextView) findViewById(R.id.doSignup);
        doLogin = (Button) findViewById(R.id.doLogin);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        sharedPreferences = getSharedPreferences(LOGINPREF, Context.MODE_PRIVATE);


        doSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignupActivity.class));

            }
        });

        doLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();
            }
        });


    }


    @Override
    protected void onStart() {
        String username = sharedPreferences.getString(LOGINPREF, "Login");
        if (!username.equals(LOGINPREF)) {
            Intent intent = new Intent(this, TravelActivity.class);
            //intent.putExtra(USER_EMAIL, username);
            //intent.putExtra(LOGINPREF, "Login");
            startActivity(intent);
            this.finish();
            Toast.makeText(this, "Hello" , Toast.LENGTH_LONG).show();
        }
        super.onStart();
    }
    public void login(){

        final String username = userName.getText().toString().trim();
        final String pass = password.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Data.LoginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("0")){

                            Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("userid", response);
                            editor.putString("isloged", "ok");
                            //SharedPreferences.Editor editor = sharedPreferences.edit();

                            Intent intent = new Intent(LoginActivity.this, TravelActivity.class);
                            //intent.putExtra(LOGINPREF, "Login");
                            intent.putExtra(LOGINPREF, username);
                            //editor.putString(USER_EMAIL, username);
                            //intent.putExtra(USER_EMAIL, username);
                            startActivity(intent);
                            editor.commit();
                            userName.setText(null);
                            password.setText(null);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", userName.getText().toString().trim());
             //   params.put("username", p.getText().toString().trim());
                params.put("password", password.getText().toString().trim());

                return params;
            }

        };

        RequestQueue requestQueue2 = Volley.newRequestQueue(this);
        requestQueue2.add(stringRequest);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            //Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onBackPressed() {
        //Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
    }

}
