package com.rifqi.peminjamanbarang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rifqi.peminjamanbarang.Common.Common;
import com.rifqi.peminjamanbarang.Model.Pinjam;
import com.rifqi.peminjamanbarang.Model.Request;
import com.rifqi.peminjamanbarang.ViewHolder.PinjamViewHolder;

public class History extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Request,PinjamViewHolder> adapter;


    FirebaseDatabase database;
    DatabaseReference requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        database = FirebaseDatabase.getInstance();
        requests = database.getReference("Requests");

        recyclerView = findViewById(R.id.listOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadPinjam(Common.currentUser.getPhone());
    }

    private void loadPinjam(String name) {
        adapter = new FirebaseRecyclerAdapter<Request, PinjamViewHolder>(
                Request.class,
                R.layout.pinjam_layout,
                PinjamViewHolder.class,
                requests.orderByChild("name").equalTo(name)
        ) {
            @Override
            protected void populateViewHolder(PinjamViewHolder viewHolder, Request model, int position) {
                viewHolder.txtPinjamId.setText(adapter.getRef(position).getKey());
                viewHolder.txtPinjamNama.setText((model.getName()));
                viewHolder.txtPinjamKeperluan.setText(model.getKeperluan());

            }
        };
        recyclerView.setAdapter(adapter);
    }
}
