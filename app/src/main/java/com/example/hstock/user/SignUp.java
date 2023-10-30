package com.example.hstock.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hstock.R;
import com.example.hstock.model.Api_User;
import com.example.hstock.model.Model_User;
import com.example.hstock.retrofitclient.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUp extends AppCompatActivity {
    EditText firstName,lastName,addRess,phoneNumber,userName,userPassword;
    Button btnRegis;
    TextView HadAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        HadAccount = findViewById(R.id.hadAccount);
        HadAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, login.class);
                startActivity(intent);




            }
        });
        initView();
        initControl();
    }

    private void initControl() {
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky();
                }
        });
    }

    private void dangky() {
        String FirstNamee=firstName.getText().toString().trim();
        String LastNamee=lastName.getText().toString().trim();
        String AddResss=addRess.getText().toString().trim();
        String PhoneNumberr=phoneNumber.getText().toString().trim();
        String UserNamee=userName.getText().toString().trim();
        String UserPasswordd=userPassword.getText().toString().trim();
        if(TextUtils.isEmpty(FirstNamee)){
            Toast.makeText(getApplicationContext(),"ban chua nhap First Name",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(LastNamee)){
            Toast.makeText(getApplicationContext(),"ban chua nhap Last Name",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(AddResss)){
            Toast.makeText(getApplicationContext(),"ban chua nhap Address",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(PhoneNumberr)){
            Toast.makeText(getApplicationContext(),"ban chua nhap Telephone Number",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(UserNamee)){
            Toast.makeText(getApplicationContext(),"ban chua nhap User Name",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(UserPasswordd)){
            Toast.makeText(getApplicationContext(),"ban chua nhap User Password",Toast.LENGTH_LONG).show();
        } else{
//            Model_User model_user=new Model_User(FirstNamee,LastNamee,AddResss,PhoneNumberr,UserNamee,UserPasswordd);
            Api_User api_user= RetrofitClient.getRetrofitInstance().create(Api_User.class);
            Call<Model_User> call = api_user.dangKy(firstName.getText().toString(),lastName.getText().toString(),addRess.getText().toString(),phoneNumber.getText().toString(),userName.getText().toString(),userPassword.getText().toString());
            call.enqueue(new Callback<Model_User>() {
                @Override
                public void onResponse(Call<Model_User> call, Response<Model_User> response) {
                    Toast.makeText(SignUp.this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Thanh cong",response.toString());

                }

                @Override
                public void onFailure(Call<Model_User> call, Throwable t) {
                    Toast.makeText(SignUp.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.i("That bai",t.toString());
                }
            });
        }
    }

    private void initView() {
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        addRess = findViewById(R.id.addRess);
        phoneNumber = findViewById(R.id.phoneNumber);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        btnRegis=findViewById(R.id.btnRegis);


    }
}


