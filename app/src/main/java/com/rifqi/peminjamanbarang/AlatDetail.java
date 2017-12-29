package com.rifqi.peminjamanbarang;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rifqi.peminjamanbarang.Database.Database;
import com.rifqi.peminjamanbarang.Model.Alat;
import com.rifqi.peminjamanbarang.Model.Pinjam;
import com.squareup.picasso.Picasso;

public class AlatDetail extends AppCompatActivity {
    TextView alat_name, alat_description;
    ImageView alat_image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCart;
    ElegantNumberButton numberButton;

    String alatId="";
    FirebaseDatabase database;
    DatabaseReference alats;
    Alat currentAlat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alat_detail);

        database = FirebaseDatabase.getInstance();
        alats = database.getReference("Alat");


        numberButton = findViewById(R.id.number_button);
        btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).addToCart(new Pinjam(
                        alatId,
                        currentAlat.getName(),
                        numberButton.getNumber()


                ));
                Toast.makeText(AlatDetail.this, "Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });


        alat_description = findViewById(R.id.alat_description);
        alat_name = findViewById(R.id.nama_alat1);
        alat_image = findViewById(R.id.alat_image);

        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        if (getIntent() != null)
            alatId = getIntent().getStringExtra("AlatId");
        if (!alatId.isEmpty()) {
            getDetailALat(alatId);
        }
    }


    private void getDetailALat(String alatId) {
        alats.child(alatId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentAlat = dataSnapshot.getValue(Alat.class);
                Picasso.with(getBaseContext()).load(currentAlat.getImage())
                        .into(alat_image);

                collapsingToolbarLayout.setTitle(currentAlat.getName());

                alat_name.setText(currentAlat.getName());
                alat_description.setText(currentAlat.getDescription());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    };