package com.example.hstock.HomeDisplay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.hstock.Cart.CartActivity;
import com.example.hstock.Profile.Profile;
import com.example.hstock.R;
import com.example.hstock.SearchProduct.SearchProductActivity;
import com.example.hstock.model.Api_Brand;
import com.example.hstock.model.Api_product;
import com.example.hstock.model.Model_Brand;
import com.example.hstock.model.Model_product;
import com.example.hstock.retrofitclient.RetrofitClient;
import com.example.hstock.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homeDisplay extends AppCompatActivity {
    private BottomNavigationView mNavigationView;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter photoAdapter;
    List<Model_Brand> list1;
    List<Model_product> list2;

    RecyclerView rcv;
    RecyclerView view1;
    //ActivityHomeDisplayBinding binding;
    ImageView btnSearch;
    ImageView btnCart,btnPersonal;

    private ArrayList<Model_product> arrProduct;
    Adapter_product adapter = new Adapter_product(arrProduct, this); // Truyền "this" là tham chiếu đến Context








    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_display);

        Anhxa();
        btnSearch =findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(homeDisplay.this, SearchProductActivity.class);
                startActivity(intent);

            }
        });
        btnCart=findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(homeDisplay.this, CartActivity.class);
                startActivity(intent);
            }
        });
        btnPersonal=findViewById(R.id.btnPersonal);
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(homeDisplay.this, Profile.class);
                startActivity(intent);
            }
        });

        //lưu thông tin người dùng




//    private void displayUserInfo(String username) {
//        // Hiển thị thông tin đăng nhập, ví dụ:
//        TextView userInfoTextView = findViewById(R.id.userInfoTextView);
//        userInfoTextView.setText("Welcome, " + username);
//    }








        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);

        photoAdapter = new PhotoAdapter(this,getListPhoto());
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        rcv =findViewById(R.id.rcv);
        view1=findViewById(R.id.view1);
        list1= new ArrayList<>();
        list2 = new ArrayList<>();
        getApiProduct();
        getApiBrand();

        mNavigationView=findViewById(R.id.bottom_nav);
    }

    private void Anhxa() {
        if(Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }
    }


    private List<photo> getListPhoto(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.nikesale30));
        list.add(new photo(R.drawable.adidasads));
        list.add(new photo(R.drawable.adidas));
        list.add(new photo(R.drawable.nikeads));
        list.add(new photo(R.drawable.levis));
        list.add(new photo(R.drawable.jd));
        list.add(new photo(R.drawable.jd1mid));
        list.add(new photo(R.drawable.jd2));
        list.add(new photo(R.drawable.jd3));
        list.add(new photo(R.drawable.jdads));
        return list;
    }
    public void getApiBrand(){
        Api_Brand apiBrand=RetrofitClient.getRetrofitInstance().create(Api_Brand.class);
        Call<List<Model_Brand>> brandCall =apiBrand.getBrand();
        brandCall.enqueue(new Callback<List<Model_Brand>>() {
            @Override
            public void onResponse(Call<List<Model_Brand>> call, Response<List<Model_Brand>> response) {
                getData1(response.body());
                list1=response.body();
                Adapter_Brand adapterBrand = new Adapter_Brand((ArrayList<Model_Brand>) list1,homeDisplay.this);
                view1.setHasFixedSize(true);
                view1.setLayoutManager(new LinearLayoutManager(homeDisplay.this,LinearLayoutManager.HORIZONTAL,false));
                view1.setAdapter(adapterBrand);
            }

            @Override
            public void onFailure(Call<List<Model_Brand>> call, Throwable t) {
                Toast.makeText(homeDisplay.this, "Loi xay ra", Toast.LENGTH_SHORT).show();
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
                Adapter_product adapterProduct= new Adapter_product((ArrayList<Model_product>) list2,homeDisplay.this);
                rcv.setHasFixedSize(true);
                rcv.setLayoutManager(new LinearLayoutManager(homeDisplay.this,LinearLayoutManager.HORIZONTAL,false));
                rcv.setAdapter(adapterProduct);

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
    private void getData1(List<Model_Brand> ls2){
        for (Model_Brand b : ls2){
            Log.i("====People",b.toString());
        }
    }

//    private void getEvenClick(){
//       mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//           @Override
//           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_trending:
//                }
//               return false;
//           }
//       });
//    }



}