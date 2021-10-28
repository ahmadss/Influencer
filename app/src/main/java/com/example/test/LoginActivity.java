package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.model.User;
import com.example.test.request.RequestLogin;
import com.example.test.result.ResultLogin;
import com.example.test.service.APIClient;
import com.example.test.service.APIInterface;
import com.example.test.utils.ProfileUtil;
import com.example.test.utils.ProgressDialogUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText input_username, input_password;
    private FrameLayout clearEmailLogin;
    private ImageButton show, hide;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_username = findViewById(R.id.input_username);
        input_password = findViewById(R.id.input_password);
        clearEmailLogin = findViewById(R.id.clearEmailLogin);
        show = findViewById(R.id.show);
        hide = findViewById(R.id.hide);

        input_username.setFocusable(true);



        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLogin();
            }
        });

        apiInterface      = APIClient.getClient(getApplicationContext()).create(APIInterface.class);


        User userPicker = new ProfileUtil().getUser(getApplicationContext());
        if (userPicker != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        initProperty();
    }

    private void submitLogin(){
        String email        = input_username.getText().toString().trim();
        String password    = input_password.getText().toString().trim();

        if (email.equals("")){
            input_username.setError("please input username");
            return;
        }
        if (password.equals("")){
            input_password.setError("please input password");
            return;
        }
        /*if (!email.equals("")){
            boolean validateMail = isEmailValid(email);
            if (!validateMail){
                input_username.setError("Format penulisan email tidak valid");
                return;
            }
        }*/



        /*Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);*/


        try {
            final ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(LoginActivity.this, ProgressDialog.STYLE_SPINNER,false);
            progressDialogUtil.show();

            RequestLogin data = new RequestLogin(email, password);
            Call<ResultLogin> callLogin = apiInterface.doLogin(data);
            callLogin.enqueue(new Callback<ResultLogin>() {
                @Override
                public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {
                    if(response.isSuccessful()){
                        String status = response.body().getStatus();
                        String message = response.body().getMessage();
                        if (status!=null && status.equals("ERR-00-000")){
//                            if (response.body().getBlock()){
                                User user = response.body().getData();
                                new ProfileUtil().save(getApplicationContext(), user);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
//                            }else{
//                                Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
//                            }


                        }else{
                            Toast.makeText(LoginActivity.this,message,Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        try {
                            JSONObject j = new JSONObject(response.errorBody().string());
                            Toast.makeText(LoginActivity.this,j.getString("message"),Toast.LENGTH_SHORT).show();
                        }catch (Exception e){
                            e.getMessage();
                        }
                    }
                    progressDialogUtil.dismiss();

                }

                @Override
                public void onFailure(Call<ResultLogin> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                    Toast.makeText(LoginActivity.this,"Invalid connection",Toast.LENGTH_SHORT).show();
                    progressDialogUtil.dismiss();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }



    private void initProperty(){
        input_username.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (input_username.getText().toString().length() == 0) {
                    input_username.setVisibility(View.VISIBLE);
                    clearEmailLogin.setVisibility(View.INVISIBLE);
                } else {
                    input_username.setError(null);
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearEmailLogin.setVisibility(View.VISIBLE);
            }
        });

        input_password.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (input_password.getText().toString().length() == 0) {
                    show.setVisibility(View.INVISIBLE);
                    hide.setVisibility(View.INVISIBLE);
                }else if(hide.getVisibility()==View.VISIBLE){
                    show.setVisibility(View.INVISIBLE);
                }else{
                    show.setVisibility(View.VISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_password.setTransformationMethod(null);
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_password.setTransformationMethod(new PasswordTransformationMethod());
                show.setVisibility(View.VISIBLE);
                hide.setVisibility(View.INVISIBLE);
            }
        });
        clearEmailLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_username.setText("");
                input_username.setVisibility(View.VISIBLE);
            }
        });

    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this,"Press BACK again to exit.", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }
}