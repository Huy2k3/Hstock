package com.example.hstock.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hstock.HomeDisplay.homeDisplay;
import com.example.hstock.R;
import com.example.hstock.model.Api_User;
import com.example.hstock.model.Model_User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    Button btnRegis;
    EditText edtEmail, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("my_preferences",MODE_PRIVATE);
        String user= preferences.getString("username","");
        if (user.length()>0){
            Intent intent= new Intent(login.this, homeDisplay.class);
            startActivity(intent);
        }else {
            setContentView(R.layout.activity_login);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRegis = findViewById(R.id.btnRegis);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, SignUp.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sử dụng Retrofit để gọi API User từ tệp Api_User
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.0.100/server/user.php/") // Thay URL bằng URL cụ thể của API User
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api_User apiUser = retrofit.create(Api_User.class);

                // Gọi API để lấy danh sách người dùng
                Call<List<Model_User>> call = apiUser.getUser();
                call.enqueue(new Callback<List<Model_User>>() {
                    @Override
                    public void onResponse(Call<List<Model_User>> call, Response<List<Model_User>> response) {
                        if (response.isSuccessful()) {
                            List<Model_User> userList = response.body();
                            // Kiểm tra thông tin đăng nhập
                            String email = edtEmail.getText().toString();
                            String password = edtPass.getText().toString();

                            for (Model_User user : userList) {
                                if (email.equals(user.getUserName()) && password.equals(user.getUserPassword())) {
                                    // Đăng nhập thành công, chuyển đến màn hình homeDisplay
                                    // Lưu tên người dùng vào SharedPreferences sau khi đăng nhập thành công
                                    String username = edtEmail.getText().toString();
                                    // Đây là ví dụ, bạn cần thay thế bằng cách lấy tên người dùng từ form đăng nhập.
                                    SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", username);
                                    editor.apply();
                                    editor.commit();

                                    Intent intent = new Intent(login.this, homeDisplay.class);
                                    startActivity(intent);
                                    return; // Thoát vòng lặp sau khi tìm thấy người dùng hợp lệ
                                }
                            }

                            // Nếu vòng lặp không tìm thấy người dùng hợp lệ
                            Toast.makeText(login.this, "Đăng nhập không thành công. Kiểm tra lại thông tin.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Xử lý lỗi kết nối hoặc lỗi từ API
                            Toast.makeText(login.this, "Có lỗi xảy ra khi gọi API.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Model_User>> call, Throwable t) {
                        // Xử lý lỗi kết nối
                        Toast.makeText(login.this, "Lỗi kết nối.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
