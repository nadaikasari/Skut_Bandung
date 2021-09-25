package com.sttbandung.skutbandung.LoginRegister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sttbandung.skutbandung.Fragment.UserFragment;
import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.adapter.UserAdapter;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.handler.HttpHandler;
import com.sttbandung.skutbandung.pojo.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    EditText txtEmail, txtPass;
    Button login;

    private RecyclerView recyclerView;
    private UserAdapter adapter;
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

    private void getData() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("user");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String id = hit.getString("id");
                                name = hit.getString("nama");
                                uid = hit.getString("uid");
                                email = hit.getString("email");
                                notlpn = hit.getString("nohp");
                                image = hit.getString("foto");
                                saldo = hit.getString("saldo");

                                arrayList.add(new user(id, name, uid, email, notlpn, image, saldo));
                            }
                            adapter = new UserAdapter(arrayList, LoginActivity.this);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void gotoMain() {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("uid", uid);
        bundle.putString("email", email);
        bundle.putString("nohp", notlpn);
        bundle.putString("image", image);
        bundle.putString("saldo", saldo);

        UserFragment fragobj = new UserFragment();
        fragobj.setArguments(bundle);
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
                        email = dataObj.getString("email");
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