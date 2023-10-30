package com.example.hstock.HomeDisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.hstock.R;
import com.example.hstock.model.Model_Brand;

import java.util.ArrayList;

public class Adapter_Brand extends RecyclerView.Adapter<Adapter_Brand.viewHolder> {
    ArrayList<Model_Brand> arrBrand;
    Context bContext;

    public Adapter_Brand(ArrayList<Model_Brand> arrBrand, Context bContext) {
        this.arrBrand = arrBrand;
        this.bContext = bContext;
    }

    @NonNull
    @Override
    public Adapter_Brand.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_brand,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Brand.viewHolder holder, int position) {
        if(arrBrand !=null && position<arrBrand.size() && bContext!=null){
            Glide.with(bContext).load(arrBrand.get(position).getImage()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40))).into(holder.imgBrand);
            //Glide.with(bContext).load("https://cdn.flightclub.com/750/TEMPLATE/264266/1.jpg");
        }
    }

    @Override
    public int getItemCount() {
        return arrBrand.size();
    }
    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imgBrand;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            imgBrand=itemView.findViewById(R.id.imgBrand);
        }
    }
}
