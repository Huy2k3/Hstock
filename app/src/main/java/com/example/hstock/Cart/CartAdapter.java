package com.example.hstock.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hstock.R;
import com.example.hstock.model.EventBus.TinhTongEvent;
import com.example.hstock.model.Model_Cart;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHoler> {
    Context context;
    List<Model_Cart> cartList;

    public CartAdapter(Context context, List<Model_Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public viewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_cart,parent,false);
        return new viewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHoler holder, int position) {
        Model_Cart modelCart=cartList.get(position);
        holder.nameProduct.setText(modelCart.getProduct_name());
//        holder.priceProduct.setText(modelCart.getPrice());
        holder.soluong.setText(modelCart.getSoluong() +" ") ;
        Glide.with(context).load(modelCart.getProduct_img()).into(holder.imginCart);
        holder.priceProduct.setText("US$"+modelCart.getPrice());
        holder.size.setText( "Size : " +modelCart.getSize() + " ");
        int gia = modelCart.getSoluong() * modelCart.getPrice();
        holder.price2.setText("US$" + gia);
        holder.setListenner(new ImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                if(giatri==1){
                    if(cartList.get(pos).getSoluong() > 1){
                        int soluongmoi = cartList.get(pos).getSoluong() - 1;
                        cartList.get(pos).setSoluong(soluongmoi);
                    }
                }else if(giatri==2){
                    if(cartList.get(pos).getSoluong() < 6){
                        int soluongmoi = cartList.get(pos).getSoluong() + 1;
                        cartList.get(pos).setSoluong(soluongmoi);
                    }

                }
                holder.soluong.setText(cartList.get(pos).getSoluong()+" ") ;
                int gia =cartList.get(pos).getSoluong() * cartList.get(pos).getPrice();
                holder.price2.setText("US$" + gia);
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class viewHoler extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imginCart;
        TextView nameProduct,priceProduct,soluong,price2;
        TextView size;
        ImageClickListenner listenner;
        Button tru,cong;
        public viewHoler(@NonNull View itemView) {
            super(itemView);
            imginCart = itemView.findViewById(R.id.imginCart);
            nameProduct=itemView.findViewById(R.id.nameProduct);
            priceProduct=itemView.findViewById(R.id.priceProduct);
            soluong=itemView.findViewById(R.id.soluong);
            size=itemView.findViewById(R.id.size);
            price2=itemView.findViewById(R.id.price2);
            tru =itemView.findViewById(R.id.tru);
            cong=itemView.findViewById(R.id.cong);
            //sizeInCart.setAdapter(adapter);
            //event click cong ;tru
            cong.setOnClickListener(this);
            tru.setOnClickListener(this);
        }

        public void setListenner(ImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {
            if(view == tru){
                listenner.onImageClick(view ,getAdapterPosition(),1);
            }else if(view == cong){
                listenner.onImageClick(view,getAdapterPosition(),2);
            }
        }
    }
}
