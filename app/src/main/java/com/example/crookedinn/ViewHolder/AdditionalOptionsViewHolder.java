package com.example.crookedinn.ViewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.crookedinn.Interface.ItemClickListener;
import com.example.crookedinn.R;

public class AdditionalOptionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView Categorytitle;
    public RelativeLayout layout_view;
    public ItemClickListener listener1;


public AdditionalOptionsViewHolder(View itemView){
    super(itemView);

    Categorytitle = itemView.findViewById(R.id.CategoryOptionsTitle);
    layout_view = itemView.findViewById(R.id.layout_view_options);
}
public void setItemClickListener(ItemClickListener listener1){
    this.listener1 = listener1;
}

    @Override
    public void onClick(View v) {

    }
}
