package com.example.hstock.SearchProduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.hstock.Cart.CartActivity;
import com.example.hstock.HomeDisplay.Adapter_product;
import com.example.hstock.HomeDisplay.homeDisplay;
import com.example.hstock.Profile.Profile;
import com.example.hstock.R;
import com.example.hstock.model.Api_product;
import com.example.hstock.model.Model_product;
import com.example.hstock.retrofitclient.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductActivity extends AppCompatActivity {
    RecyclerView rcvProduct;
    List<Model_product> list2;
    ArrayList<Model_product> arrProduct;
    Adapter_product adapter = new Adapter_product(arrProduct, this); // Truyền "this" là tham chiếu đến Context
    ImageView btnTrending,btnCart,btnPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);
        rcvProduct=findViewById(R.id.rcvProduct);
        list2=new ArrayList<>();
        getApiProduct();
        //RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        btnTrending=findViewById(R.id.btnTrending);
        btnCart=findViewById(R.id.btnCart);
        btnPersonal=findViewById(R.id.btnPersonal);
        btnTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SearchProductActivity.this, homeDisplay.class);
                startActivity(intent);
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SearchProductActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SearchProductActivity.this, Profile.class);
                startActivity(intent);
            }
        });
    }


    public void getApiProduct(){
        Api_product apiProduct= RetrofitClient.getRetrofitInstance().create(Api_product.class);
        Call<List<Model_product>> call= apiProduct.getProduct();
        call.enqueue(new Callback<List<Model_product>>() {
            @Override
            public void onResponse(Call<List<Model_product>> call, Response<List<Model_product>> response) {
                getData2(response.body());
                list2=response.body();
                Adapter_product adapterProduct= new Adapter_product((ArrayList<Model_product>) list2, SearchProductActivity.this);
                rcvProduct.setHasFixedSize(true);
                rcvProduct.setLayoutManager(new GridLayoutManager(SearchProductActivity.this,2));
                //rcvProduct.setLayoutManager(new LinearLayoutManager(SearchProductActivity.this,LinearLayoutManager.VERTICAL,false));
                rcvProduct.setAdapter(adapterProduct);

            }


            public void onFailure(Call<List<Model_product>> call, Throwable t) {
                Log.e("API Error", "Lỗi xảy ra", t);
                //Toast.makeText(homeDisplay.this, "Loi xay ra", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void getData2(List<Model_product> ls1){
        for (Model_product p : ls1){
            Log.i("====People",p.toString());
        }
    }
}