package com.example.hstock.Cart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hstock.HomeDisplay.homeDisplay;
import com.example.hstock.Payment.PaymentActivity;
import com.example.hstock.Profile.Profile;
import com.example.hstock.R;
import com.example.hstock.SearchProduct.SearchProductActivity;
import com.example.hstock.model.EventBus.TinhTongEvent;
import com.example.hstock.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

public class CartActivity extends AppCompatActivity {
    TextView totalPrice,giohangtrong;
    Button buttonConfirm;
    ImageView btnTrending,btnSearch,btnPersonal;
    RecyclerView rcvCart;
    CartAdapter cartAdapter;
//    List<Model_Cart> cartList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        initControl();
        TotalPrice();
        buttonConfirm=findViewById(R.id.btnConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
        btnTrending=findViewById(R.id.btnTrending);
        btnSearch=findViewById(R.id.btnSearch);
        btnPersonal=findViewById(R.id.btnPersonal);
        btnTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, homeDisplay.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, SearchProductActivity.class);
                startActivity(intent);
            }
        });
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    private void TotalPrice() {
        int tongtien = 0;
        for(int i = 0 ;i<Utils.manggiohang.size();i++){
            tongtien=tongtien+ (Utils.manggiohang.get(i).getPrice() * Utils.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("#####");
        totalPrice.setText( "US$" +decimalFormat.format(tongtien));
    }

    private void initControl() {
        rcvCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        rcvCart.setLayoutManager(layoutManager);
        if(Utils.manggiohang.size() == 0){
            giohangtrong.setVisibility(View.VISIBLE);
        }
        else {
            cartAdapter=new CartAdapter(getApplicationContext(),Utils.manggiohang);
            rcvCart.setAdapter(cartAdapter);
        }
    }

    private void initView() {
        totalPrice =findViewById(R.id.totalPrice);
        rcvCart =findViewById(R.id.rcvCart);
        giohangtrong=findViewById(R.id.giohangtrong);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void TinhTienEvent(TinhTongEvent event){
        if(event != null){
            TotalPrice();
        }
    }
}