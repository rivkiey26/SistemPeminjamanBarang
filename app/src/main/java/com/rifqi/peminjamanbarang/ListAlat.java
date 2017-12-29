package com.rifqi.peminjamanbarang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rifqi.peminjamanbarang.Interface.ItemClickListener;
import com.rifqi.peminjamanbarang.Model.Alat;
import com.rifqi.peminjamanbarang.ViewHolder.AlatViewHolder;
import com.squareup.picasso.Picasso;

public class ListAlat extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference alatList;
    String categoryId = "";
    FirebaseRecyclerAdapter<Alat, AlatViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_alat);
        database = FirebaseDatabase.getInstance();
        alatList = database.getReference("Alat");


        recyclerView = findViewById(R.id.recyclerAlat);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (getIntent() != null) {
            categoryId = getIntent().getStringExtra("CategoryId");
            if (!categoryId.isEmpty() && categoryId != null) {
                loadListAlat();
            }
        }


    }

    private void loadListAlat() {
        adapter = new FirebaseRecyclerAdapter<Alat, AlatViewHolder>(
                Alat.class,
                R.layout.alat_item,
                AlatViewHolder.class,
                alatList.orderByChild("MenuId").equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(AlatViewHolder viewHolder, Alat model, int position) {
                viewHolder.textAlatName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageAlat);

                final Alat local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent alatDetail = new Intent(ListAlat.this, AlatDetail.class);
                        alatDetail.putExtra("AlatId", adapter.getRef(position).getKey());
                        startActivity(alatDetail);
                    }
                });
            }
        };
        Log.d("TAG", "" + adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
