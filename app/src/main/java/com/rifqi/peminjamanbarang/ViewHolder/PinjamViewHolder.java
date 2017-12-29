package com.rifqi.peminjamanbarang.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rifqi.peminjamanbarang.Interface.ItemClickListener;
import com.rifqi.peminjamanbarang.R;

/**
 * Created by USER on 28/12/2017.
 */

public class PinjamViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtPinjamId,txtPinjamNama,txtPinjamKeperluan;
    private ItemClickListener itemClickListener;
    public PinjamViewHolder(View itemView) {
        super(itemView);
        txtPinjamId = itemView.findViewById(R.id.pinjam_id);
        txtPinjamNama = itemView.findViewById(R.id.pinjam_nama);
        txtPinjamKeperluan = itemView.findViewById(R.id.pinjam_keperluan);
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
