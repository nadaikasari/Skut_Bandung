package com.sttbandung.skutbandung.mvvm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import com.sttbandung.skutbandung.handler.Config;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BerandaViewModel extends ViewModel {

    private final int timeoutConnection = 700000;
    private static final String TAG = "BerandaViewModel";

    private final MutableLiveData<Integer> _ResultTransaksiDone = new MutableLiveData<>();
    private final MutableLiveData<Integer> _ResultTransaksiDelayed = new MutableLiveData<>();
    private final MutableLiveData<Integer> _ResultTransaksiNotUsed = new MutableLiveData<>();
    private final MutableLiveData<String> _ResultSaldoUser = new MutableLiveData<>();
    private final MutableLiveData<String> _messageError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();

    public LiveData<Integer> getTransaksiDone() {
        return _ResultTransaksiDone;
    }
    public LiveData<Integer> getTransaksiDelayed() {
        return _ResultTransaksiDelayed;
    }
    public LiveData<Integer> getTransaksiNotUsed() {
        return _ResultTransaksiNotUsed;
    }
    public LiveData<String> getResultSaldo() {
        return _ResultSaldoUser;
    }
    public LiveData<String> messageError() {
        return _messageError;
    }
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void getStatusDone(String Id) {
        _isLoading.setValue(true);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.TRANSAKSI_STATUS1 + Id;
        client.setTimeout(timeoutConnection);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Jika koneksi berhasil
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("transaksi");
                    int totalCount = jsonArray.length();
                    _ResultTransaksiDone.postValue(totalCount);
                    _isLoading.setValue(false);
                } catch (Exception e) {
                    _messageError.setValue(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
                _isLoading.setValue(false);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                _messageError.setValue(errorMessage);
            }
        });
    }

    public void getStatusDelayed(String Id) {
        _isLoading.setValue(true);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.TRANSAKSI_STATUS2 + Id;
        client.setTimeout(timeoutConnection);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Jika koneksi berhasil
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("transaksi");
                    int totalCount = jsonArray.length();
                    _ResultTransaksiDelayed.postValue(totalCount);
                    _isLoading.setValue(false);
                } catch (Exception e) {
                    _messageError.setValue(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
                _isLoading.setValue(false);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                _messageError.setValue(errorMessage);
            }
        });
    }

    public void getStatusUnUsed(String Id) {
        _isLoading.setValue(true);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.TRANSAKSI_STATUS3 + Id;
        client.setTimeout(timeoutConnection);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Jika koneksi berhasil
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("transaksi");
                    int totalCount = jsonArray.length();
                    _ResultTransaksiNotUsed.postValue(totalCount);
                    _isLoading.setValue(false);
                } catch (Exception e) {
                    _messageError.setValue(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
                _isLoading.setValue(false);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                _messageError.setValue(errorMessage);
            }
        });
    }

    public void getSaldoUser(String email) {
        _isLoading.setValue(true);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = Config.LOGIN + email;
        client.setTimeout(timeoutConnection);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // Jika koneksi berhasil
                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("user");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String saldo = hit.getString("saldo");
                        _ResultSaldoUser.postValue(saldo);
                        _isLoading.setValue(false);
                    }
                } catch (Exception e) {
                    _messageError.setValue(e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // Jika koneksi gagal
                _isLoading.setValue(false);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                _messageError.setValue(errorMessage);
            }
        });
    }
}
