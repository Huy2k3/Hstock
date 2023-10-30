package com.example.hstock.DetailProduct;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.hstock.Cart.CartActivity;
import com.example.hstock.R;
import com.example.hstock.SearchProduct.SearchProductActivity;
import com.example.hstock.model.Api_ProductDetail;
import com.example.hstock.model.Model_Cart;
import com.example.hstock.model.Model_ProductDetail;
import com.example.hstock.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class DetailProductActivity extends AppCompatActivity {
    TextView Productname,colorway,Price,txtProductDetails,style,colorway2,Retailprice,Releasedate,desc;
    TextView insertStyle,insertColor,insertRetail,insertRelease,insertDesc;
    ImageView imgProductDetail,imgCart,back;
    Button btnAddtoCart;
    private Context context;

    Spinner spinnerSize,spinnerSoluong;
    Model_ProductDetail modelProductDetail;
    NotificationBadge menu_sl;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        initView();
        initControl();
        context = this;

        // Lấy dữ liệu từ Intent
        int id = getIntent().getIntExtra("product_detail_id", 0);
//
//        Model_ProductDetail modelProductDetail = getProductDetailById(product_detail_id);

        if (id != 0) {
            getProductDetailById(id);
        }



        Integer[] ls ={35,36,37,38,39,40,41,42,43};
        ArrayAdapter<Integer> adapter
                =new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,ls);
        spinnerSize.setAdapter(adapter);

        Integer[] so ={1,2,3,4,5};
        ArrayAdapter<Integer> adapter1=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinnerSoluong.setAdapter(adapter1);



    }

    private void initControl() {
        btnAddtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themgiohang();




            }
        });

    }

    private void themgiohang() {
        if (modelProductDetail != null) {
            if (Utils.manggiohang.size() > 0) {
                boolean flag = false;
                int soluong = Integer.parseInt(spinnerSoluong.getSelectedItem().toString());
                int sizeShoes = Integer.parseInt(spinnerSize.getSelectedItem().toString());
                for (int i = 0; i < Utils.manggiohang.size(); i++) {
                    if (Utils.manggiohang.get(i).getCartid() == modelProductDetail.getId()) {
                        Utils.manggiohang.get(i).setSize( Utils.manggiohang.get(i).getSize());
                        Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());
                        int price = modelProductDetail.getPrice() ;
                        Utils.manggiohang.get(i).setPrice(price);
                        flag = true;
                    }
                }
                if (flag == false) {
                    int price = modelProductDetail.getPrice() ;
                    Model_Cart modelCart = new Model_Cart();
                    modelCart.setSize(sizeShoes);
                    modelCart.setPrice(price);
                    modelCart.setSoluong(soluong);
                    modelCart.setCartid(modelProductDetail.getId());
                    modelCart.setProduct_name(modelProductDetail.getProductname());
                    modelCart.setProduct_img(modelProductDetail.getImgProductDetail());
                    Utils.manggiohang.add(modelCart);
                }
            } else {
                int sizeShoes = Integer.parseInt(spinnerSize.getSelectedItem().toString());
                int soluong = Integer.parseInt(spinnerSoluong.getSelectedItem().toString());
                int price = modelProductDetail.getPrice() ;
                Model_Cart modelCart = new Model_Cart();
                modelCart.setPrice(price);
                modelCart.setSoluong(soluong);
                modelCart.setSize(sizeShoes);
                modelCart.setCartid(modelProductDetail.getId());
                modelCart.setProduct_name(modelProductDetail.getProductname());
                modelCart.setProduct_img(modelProductDetail.getImgProductDetail());
                Utils.manggiohang.add(modelCart);
            }
//            int totalPrice = 0;
//            for(int i = 0;i<Utils.manggiohang.size();i++){
//                totalPrice =  Utils.manggiohang.get(i).getSoluong();
//            }
            menu_sl.setText(String.valueOf(Utils.manggiohang.size()));
        }
//        else {
//            // Xử lý trường hợp modelProductDetail là null
//            // Ví dụ: Hiển thị thông báo cho người dùng
//            showErrorDialog("Lỗi", "Không thể thêm sản phẩm vào giỏ hàng.");
//        }

    }

//    private void showErrorDialog(String title, String message) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(DetailProductActivity.this);
//        builder.setTitle(title)
//                .setMessage(message)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // Đóng cửa sổ thông báo nếu người dùng chọn OK
//                        dialog.dismiss();
//                    }
//                });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }



    private void initView() {
        Productname=findViewById(R.id.Productname);
        colorway=findViewById(R.id.colorway);
        Price=findViewById(R.id.price);
        txtProductDetails=findViewById(R.id.txtProductDetails);
        style=findViewById(R.id.colorway);
        colorway2=findViewById(R.id.colorway2);
        Retailprice=findViewById(R.id.Retailprice);
        Releasedate=findViewById(R.id.Releasedate);
        desc=findViewById(R.id.desc);
        imgProductDetail=findViewById(R.id.imgProductDetail);
        btnAddtoCart=findViewById(R.id.btnAddtoCart);
        insertStyle=findViewById(R.id.insertStyle);
        insertColor=findViewById(R.id.insertColor);
        insertRetail=findViewById(R.id.insertRetail);
        insertRelease=findViewById(R.id.insertRelease);
        insertDesc=findViewById(R.id.insertDesc);
        spinnerSoluong=findViewById(R.id.spinnerSoluong);
        spinnerSize=findViewById(R.id.spinnerSize);
        menu_sl=findViewById(R.id.menu_sl);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent search = new Intent(getApplicationContext(), SearchProductActivity.class);
                startActivity(search);
            }
        });
        imgCart=findViewById(R.id.imgCart);
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart=new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });
        if(Utils.manggiohang!=null){
            menu_sl.setText(String.valueOf(Utils.manggiohang.size()));
//            int totalPrice = 0;
//            for(int i = 0;i<Utils.manggiohang.size();i++){
//                totalPrice = totalPrice + Utils.manggiohang.get(i).getSoluong();
//            }

        }
    }


    private void getProductDetailById(int id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.100/server/productCategory.php/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_ProductDetail apiProductDetail = retrofit.create(Api_ProductDetail.class); // Tạo interface API

        Call<Model_ProductDetail> call = apiProductDetail.getProductDetail( id);

        call.enqueue(new Callback<Model_ProductDetail>() {
            public void onResponse(Call<Model_ProductDetail> call, Response<Model_ProductDetail> response) {
                if (response.isSuccessful()) {
                     modelProductDetail = response.body();
                    if (modelProductDetail != null) {
                        // Ở đây, bạn có thể sử dụng thông tin chi tiết sản phẩm để cập nhật giao diện người dùng
                        Productname.setText(modelProductDetail.getProductname());
                        colorway.setText(modelProductDetail.getColorway());
                        Price.setText("$"+modelProductDetail.getPrice());
                        insertStyle.setText(modelProductDetail.getStyle());
                        insertColor.setText(modelProductDetail.getColorway());
                        insertRetail.setText("$"+modelProductDetail.getRetailprice());
                        insertRelease.setText(modelProductDetail.getReleaseDate());
                        insertDesc.setText(modelProductDetail.getDesc());

                        // Load and display the product image using Glide
                        if (modelProductDetail.getImgProductDetail() != null) {
                            Glide.with(DetailProductActivity.this)
                                    .load(modelProductDetail.getImgProductDetail())  // Use the image URL from your Model_ProductDetail
                                    .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(10)))  // Apply any desired transformations
                                    .into(imgProductDetail);
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<Model_ProductDetail> call, Throwable t) {
                t.printStackTrace();
                showErrorDialog( "Lỗi kết nối", "Không thể kết nối đến máy chủ. Vui lòng kiểm tra kết nối internet và thử lại.");
            }

            private void showErrorDialog(String title, String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(title)
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Đóng cửa sổ thông báo nếu người dùng chọn OK
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }



        });
    }
}