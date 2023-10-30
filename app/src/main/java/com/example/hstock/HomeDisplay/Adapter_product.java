package com.example.hstock.HomeDisplay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.hstock.DetailProduct.DetailProductActivity;
import com.example.hstock.R;
import com.example.hstock.model.Model_product;

import java.util.ArrayList;

public class Adapter_product extends RecyclerView.Adapter<Adapter_product.viewHolder> {
    ArrayList<Model_product> arrProduct;
    Context mcontext;
    ImageView imgProduct;


    public Adapter_product(ArrayList<Model_product> arrProduct, Context mcontext) {
        this.arrProduct = arrProduct;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public Adapter_product.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_product,parent,false);
        return new viewHolder(view);
    }
    //lay du lieu tu model product
    @Override
    public void onBindViewHolder(@NonNull Adapter_product.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        //Picasso.get().load(arrProduct.get(position).getSearch_image()).into(holder.imgProduct);

        if (arrProduct != null && position < arrProduct.size() && mcontext != null) {
//            Glide.with(mcontext)
//                    .load(arrProduct.get(position).getSearch_image())
//                    .apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40)))
//                    .into(holder.imgProduct);
//
//            holder.nameProduct.setText(""+arrProduct.get(position).getBrand_filter_facet());
//            holder.priceProduct.setText("$"+arrProduct.get(position).getPrice());
            Glide.with(mcontext).load(arrProduct.get(position).getSearch_image()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(40))).into(holder.imgProduct);
            //Glide.with(mcontext).load("https://cdn.flightclub.com/750/TEMPLATE/264266/1.jpg");
            //Glide.with(holder.imgProduct.getContext()).load(arrProduct.get(position)).into(holder.imgProduct);
            holder.nameProduct.setText(""+arrProduct.get(position).getProduct_name());
            holder.priceProduct.setText("US$"+arrProduct.get(position).getPrice());

//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Chuyển đến màn hình chi tiết sản phẩm
//                    Intent intent = new Intent(mcontext, DetailProductActivity.class);
//                    // Truyền thông tin sản phẩm đến màn hình chi tiết sản phẩm
//                    intent.putExtra("productId", arrProduct.get(position).getId());
//                    mcontext.startActivity(intent);
//                }
//            });


        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển đến màn hình chi tiết sản phẩm và truyền product_detail_id
                int product_detail_id = arrProduct.get(position).getProduct_detail_id();
                //Model_ProductDetail modelProductDetail = arrProduct.get(position);
                Intent intent = new Intent(mcontext, DetailProductActivity.class);
                intent.putExtra("product_detail_id", product_detail_id);
                mcontext.startActivity(intent);

            }
        });



        // Glide.with(mcontext).load("https://i.pinimg.com/564x/98/c0/41/98c04183bfac475e0730a5a5d998a3d3.jpg");
    }

    @Override
    public int getItemCount() {
        return arrProduct.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView nameProduct,priceProduct;
        ImageView imgProduct;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct =itemView.findViewById(R.id.nameProduct);
            priceProduct=itemView.findViewById(R.id.priceProduct);
            imgProduct=itemView.findViewById(R.id.imgProduct);
        }
    }


}
