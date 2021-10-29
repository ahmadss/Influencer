package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.test.model.Kecamatan;
import com.example.test.model.Kelurahan;
import com.example.test.model.Kota;
import com.example.test.model.Postcode;
import com.example.test.model.Provinsi;
import com.example.test.model.User;
import com.example.test.request.RequestKecamatan;
import com.example.test.request.RequestKelurahan;
import com.example.test.request.RequestKodepost;
import com.example.test.request.RequestKota;
import com.example.test.request.RequestProvinsi;
import com.example.test.result.ResultKecamatan;
import com.example.test.result.ResultKelurahan;
import com.example.test.result.ResultKodepost;
import com.example.test.result.ResultKota;
import com.example.test.result.ResultProvinsi;
import com.example.test.service.APIClient;
import com.example.test.service.APIInterface;
import com.example.test.utils.ProgressDialogUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIInterface apiInterface;
    private List<Provinsi> provinsiList = new ArrayList<>();
    private List<Kota> kotaList = new ArrayList<>();
    private List<Kecamatan> kecamatanList = new ArrayList<>();
    private List<Kelurahan> kelurahanList = new ArrayList<>();
    private List<Postcode> postcodeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void getProvinsi() {
        try {

            apiInterface = APIClient.getClient(MainActivity.this).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(MainActivity.this, ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            RequestProvinsi data = new RequestProvinsi("");
            Call<ResultProvinsi> callProvinsiList = apiInterface.getProvinsi(data);
            callProvinsiList.enqueue(new Callback<ResultProvinsi>() {
                @Override
                public void onResponse(Call<ResultProvinsi> call, Response<ResultProvinsi> response) {
                    if (response.isSuccessful()) {

                        List<Provinsi> tmpList = response.body().getData();
                        provinsiList.addAll(tmpList);
                        setSpinner();

                    } else {
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this, j.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<ResultProvinsi> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void getKota() {
        try {

            apiInterface = APIClient.getClient(MainActivity.this).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(MainActivity.this, ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            RequestKota data = new RequestKota("");
            Call<ResultKota> callProvinsiList = apiInterface.getKota(data);
            callProvinsiList.enqueue(new Callback<ResultKota>() {
                @Override
                public void onResponse(Call<ResultKota> call, Response<ResultKota> response) {
                    if (response.isSuccessful()) {

                        List<Kota> tmpList = response.body().getData();
                        kotaList.addAll(tmpList);
                        setSpinner();

                    } else {
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this, j.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<ResultKota> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void getKecamatan() {
        try {

            apiInterface = APIClient.getClient(MainActivity.this).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(MainActivity.this, ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            RequestKecamatan data = new RequestKecamatan("");
            Call<ResultKecamatan> callProvinsiList = apiInterface.getKecamatan(data);
            callProvinsiList.enqueue(new Callback<ResultKecamatan>() {
                @Override
                public void onResponse(Call<ResultKecamatan> call, Response<ResultKecamatan> response) {
                    if (response.isSuccessful()) {

                        List<Kecamatan> tmpList = response.body().getData();
                        kecamatanList.addAll(tmpList);
                        setSpinner();

                    } else {
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this, j.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<ResultKecamatan> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void getKelurahan() {
        try {
            apiInterface = APIClient.getClient(MainActivity.this).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(MainActivity.this, ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            RequestKelurahan data = new RequestKelurahan("");
            Call<ResultKelurahan> callProvinsiList = apiInterface.getKelurahan(data);
            callProvinsiList.enqueue(new Callback<ResultKelurahan>() {
                @Override
                public void onResponse(Call<ResultKelurahan> call, Response<ResultKelurahan> response) {
                    if (response.isSuccessful()) {

                        List<Kelurahan> tmpList = response.body().getData();
                        kelurahanList.addAll(tmpList);
                        setSpinner();

                    } else {
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this, j.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<ResultKelurahan> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void getKodepost() {
        try {

            apiInterface = APIClient.getClient(MainActivity.this).create(APIInterface.class);
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(MainActivity.this, ProgressDialog.STYLE_SPINNER, false);
            progressDialogUtil.show();

            RequestKodepost data = new RequestKodepost("");
            Call<ResultKodepost> callProvinsiList = apiInterface.getKodePos(data);
            callProvinsiList.enqueue(new Callback<ResultKodepost>() {
                @Override
                public void onResponse(Call<ResultKodepost> call, Response<ResultKodepost> response) {
                    if (response.isSuccessful()) {

                        List<Postcode> tmpList = response.body().getData();
                        postcodeList.addAll(tmpList);
                        setSpinner();

                    } else {
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(MainActivity.this, j.getString("message"), Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();
                }

                @Override
                public void onFailure(Call<ResultKodepost> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    progressDialogUtil.dismiss();

                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void fillSpinnerProvinsi(){

        final String[] lstBankAccount = new String[provinsiList.size()];
        for (int i = 0; i < provinsiList.size(); i++) {
            String sProvId = provinsiList.get(i).getRegion_id();
            String sProvName = provinsiList.get(i).getRegion_name();
            lstBankAccount[i + 1] = sProvId + " - " + sProvName;

        }

        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, lstBankAccount);

        adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_provinsi.setAdapter(adapterBank);
        spn_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getKota();
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fillSpinnerKota(){

        final String[] lstBankAccount = new String[provinsiList.size()];
        for (int i = 0; i < provinsiList.size(); i++) {
            String sProvId = provinsiList.get(i).getRegion_id();
            String sProvName = provinsiList.get(i).getRegion_name();
            lstBankAccount[i + 1] = sProvId + " - " + sProvName;

        }

        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, lstBankAccount);

        adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_provinsi.setAdapter(adapterBank);
        spn_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getKota();
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fillSpinnerKecamatan(){

        final String[] lstBankAccount = new String[provinsiList.size()];
        for (int i = 0; i < provinsiList.size(); i++) {
            String sProvId = provinsiList.get(i).getRegion_id();
            String sProvName = provinsiList.get(i).getRegion_name();
            lstBankAccount[i + 1] = sProvId + " - " + sProvName;

        }

        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, lstBankAccount);

        adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_provinsi.setAdapter(adapterBank);
        spn_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getKota();
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fillSpinnerKelurahan(){

        final String[] lstBankAccount = new String[provinsiList.size()];
        for (int i = 0; i < provinsiList.size(); i++) {
            String sProvId = provinsiList.get(i).getRegion_id();
            String sProvName = provinsiList.get(i).getRegion_name();
            lstBankAccount[i + 1] = sProvId + " - " + sProvName;

        }

        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, lstBankAccount);

        adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_provinsi.setAdapter(adapterBank);
        spn_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getKota();
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void fillSpinnerKodePost(){

        final String[] lstBankAccount = new String[provinsiList.size()];
        for (int i = 0; i < provinsiList.size(); i++) {
            String sProvId = provinsiList.get(i).getRegion_id();
            String sProvName = provinsiList.get(i).getRegion_name();
            lstBankAccount[i + 1] = sProvId + " - " + sProvName;

        }

        ArrayAdapter<String> adapterBank = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item, lstBankAccount);

        adapterBank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_provinsi.setAdapter(adapterBank);
        spn_provinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    getKota();
                } catch (Exception e){
                    Log.e("error", e.toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}