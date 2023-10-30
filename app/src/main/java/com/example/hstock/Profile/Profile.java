package com.example.hstock.Profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hstock.Cart.CartActivity;
import com.example.hstock.HomeDisplay.homeDisplay;
import com.example.hstock.R;
import com.example.hstock.SearchProduct.SearchProductActivity;
import com.example.hstock.user.login;

public class Profile extends AppCompatActivity {
    ImageView btnTrending,btnSearch,btnCart,btnPersonal;
    TextView account,tvSingout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnTrending=findViewById(R.id.btnTrending);
        btnSearch=findViewById(R.id.btnSearch);
        btnPersonal=findViewById(R.id.btnPersonal);
        btnCart=findViewById(R.id.btnCart);
        account=findViewById(R.id.account);
        tvSingout=findViewById(R.id.tvSingout);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("my_preferences",MODE_PRIVATE);
        String email = sharedPreferences.getString("username","");
        account.setText(email);
        btnTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, homeDisplay.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, SearchProductActivity.class);
                startActivity(intent);
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, CartActivity.class);
                startActivity(intent);
            }
        });
        tvSingout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("my_preferences",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username","");
                editor.remove("username");

                editor.commit();
                Intent intent = new Intent(Profile.this, login.class);
                startActivity(intent);
                finish();


            }
        });
    }
}