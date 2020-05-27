package com.example.crookedinn.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crookedinn.Interface.ItemClickListener;
import com.example.crookedinn.R;
import org.w3c.dom.Text;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtItemName, txtItemPrice, txtItemCategory;
    public RelativeLayout Layout_view;
    public ItemClickListener listener;


    public ProductViewHolder(View itemView) {
        super(itemView);

        txtItemName = (TextView) itemView.findViewById(R.id.item_name_layout);
        txtItemPrice = (TextView) itemView.findViewById(R.id.item_price_layout);
        txtItemCategory = (TextView) itemView.findViewById(R.id.categorynamelayout);
        Layout_view = (RelativeLayout) itemView.findViewById(R.id.layout_view);
    }

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(),false);
    }
}
