package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.databinding.ActivityLoginBinding;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.handler.HttpHandler;
import com.sttbandung.skutbandung.pojo.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private final String TAG = LoginActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    public ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout no title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(v -> new Login().execute());
    }

    @SuppressLint("StaticFieldLeak")
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
            String jsonStr = sh.makeServiceCall(Config.LOGIN + binding.inputEmailLogin.getText());

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray dataArray = jsonObj.getJSONArray("user");

                    // looping through All Contacts
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        User data = new User();
                        data.setId(dataObj.getString("id"));
                        data.setUid(dataObj.getString("uid"));
                        data.setEmail(dataObj.getString("email"));
                        data.setNama(dataObj.getString("name"));
                        data.setNotlpn(dataObj.getString("nohp"));
                        data.setImage(dataObj.getString("foto"));
                        data.setSaldo(dataObj.getString("saldo"));
                        list.add(data);
                        sendData(data);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                            "Json parsing error: " + e.getMessage(),
                            Toast.LENGTH_LONG)
                            .show());

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Cant get data check ur json",
                        Toast.LENGTH_LONG)
                        .show());

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

    /**
     * mengirim data
     */
    public void sendData(User user) {
        Intent moveWithObjectIntent = new Intent(LoginActivity.this, MainActivity.class);
        moveWithObjectIntent.putExtra(MainActivity.EXTRA_DATA, user);
        startActivity(moveWithObjectIntent);
    }
}