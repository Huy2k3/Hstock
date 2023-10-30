package com.example.hstock.Payment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.hstock.CheckConnection.Checkconnection;
import com.example.hstock.R;

public class PaymentActivity extends AppCompatActivity {
    EditText edTName,edTAdd,edtTele;
    Spinner spinner;

    ImageView imageBack;
    Button btnPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initView();
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(Checkconnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            Checkconnection.ShowToast_Short(getApplicationContext(),"Ban hay kiem tra lai ket noi !!!");
        }
    }

    private void EventButton() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten=edTName.getText().toString().trim();
                String diachi=edTAdd.getText().toString().trim();
                String sdt=edtTele.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0&&diachi.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                    StringRequest stringRequest = new StringRequest(Method.POST);
                }else{
                    Checkconnection.ShowToast_Short(getApplicationContext(),"Du lieu khong duoc de trong");
                }
            }
        });
    }

    private void initView() {
        edTName=findViewById(R.id.edTName);
        edTAdd=findViewById(R.id.edTAdd);
        edtTele=findViewById(R.id.edtTele);
        spinner=findViewById(R.id.spinner);
        imageBack=findViewById(R.id.imageBack);
        btnPayment=findViewById(R.id.btnPayment);
    }
}