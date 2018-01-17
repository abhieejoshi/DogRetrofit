package com.example.abhinav.dogsretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class SignUpActivity extends AppCompatActivity {
    EditText etmail,etpass;
    Button btnSignUp,btnDirectApi;
    String email,pass;
    private SignUpInterface signUpInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etmail = findViewById(R.id.etSignupEmail);
        etpass = findViewById(R.id.etSignUpPass);
        btnSignUp = findViewById(R.id.buttonSignUp);
        btnDirectApi = findViewById(R.id.btnDirectApi);
        signUpInterface = SignUpClient.getClient().create(SignUpInterface.class);

        btnDirectApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etmail.getText().toString();
                pass = etpass.getText().toString();
               Call<SignUpDataModel> call= signUpInterface.sendData(email,pass);
                call.enqueue(new Callback<SignUpDataModel>() {
                    @Override
                    public void onResponse(Call<SignUpDataModel> call, Response<SignUpDataModel> response) {
                        SignUpDataModel signUpDataModel= response.body();
                        Log.i("Signup: onresponse",signUpDataModel.getResponse());

                            Toast.makeText(SignUpActivity.this,response.body().getResponse(),
                                    Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onFailure(Call<SignUpDataModel> call, Throwable t) {
                        Log.i("Signup: onFailure","onfail"+t.getMessage() );

                    }
                });


            }
        });


    }
}
