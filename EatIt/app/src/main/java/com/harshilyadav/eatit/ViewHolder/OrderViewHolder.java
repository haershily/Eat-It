package com.harshilyadav.eatit.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.harshilyadav.eatit.Interface.ItemClickListener;
import com.harshilyadav.eatit.R;

/**
 * Created by harshilyadav on 21/10/17.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtOrderId,txtOrderStatus,txtOrderPhone,txtOrderAddress;

    private ItemClickListener itemClickListener;

    public OrderViewHolder(View itemView) {
        super(itemView);
        txtOrderId=(TextView)itemView.findViewById(R.id.orderId);
        txtOrderAddress=(TextView)itemView.findViewById(R.id.orderAddress);
        txtOrderPhone=(TextView)itemView.findViewById(R.id.orderPhone);
        txtOrderStatus=(TextView)itemView.findViewById(R.id.orderStatus);

        itemView.setOnClickListener(this);
    }

    public OrderViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
