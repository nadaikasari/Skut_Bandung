package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.handler.HttpHandler;
import com.sttbandung.skutbandung.pojo.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    EditText txtEmail, txtPass;
    Button login;

    private ArrayList<user> arrayList;
    private RequestQueue mRequestQueue;
    String url, id, name, uid, email, notlpn, image, saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.input_email_login);
        txtPass = findViewById(R.id.input_pass_login);
        login = findViewById(R.id.btn_login);

        arrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        url = Config.LOGIN + txtEmail.getText();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login().execute();
            }
        });
    }

    private class Login extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(Config.LOGIN + txtEmail.getText());

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray dataArray = jsonObj.getJSONArray("user");

                    // looping through All Contacts
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        id = dataObj.getString("id");
                        uid = dataObj.getString("uid");
                        email = dataObj.getString("email");
                        name = dataObj.getString("name");
                        notlpn = dataObj.getString("nohp");
                        image = dataObj.getString("foto");
                        saldo = dataObj.getString("saldo");

                        if (dataObj == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this,
                                            "Username atau Password salah",
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            });
                        } else {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("id",id);
                            intent.putExtra("uid",uid);
                            intent.putExtra("email",email);
                            intent.putExtra("nama",name);
                            intent.putExtra("tlpn",notlpn);
                            intent.putExtra("foto",image);
                            intent.putExtra("saldo",saldo);
                            startActivity(intent);
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Cant get data check ur json",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

    }
}