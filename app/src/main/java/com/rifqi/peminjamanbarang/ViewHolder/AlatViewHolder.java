package com.rifqi.peminjamanbarang.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rifqi.peminjamanbarang.Interface.ItemClickListener;
import com.rifqi.peminjamanbarang.R;

/**
 * Created by USER on 28/12/2017.
 */

public class AlatViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textAlatName;
    public ImageView imageAlat;
    private ItemClickListener itemClickListener;

    public AlatViewHolder(View itemView) {
        super(itemView);
        textAlatName = itemView.findViewById(R.id.perangkat_name);
        imageAlat = itemView.findViewById(R.id.image_perangkat);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
