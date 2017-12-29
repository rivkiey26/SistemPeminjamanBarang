package com.rifqi.peminjamanbarang;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rifqi.peminjamanbarang.Common.Common;
import com.rifqi.peminjamanbarang.Model.User;

public class SignIn extends AppCompatActivity {
    private Button btnSignIn;
    private MaterialEditText edtName,edtPassword;
    FirebaseDatabase database;
    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        edtName = findViewById(R.id.edtNameSignIn);
        edtPassword = findViewById(R.id.edtPasswordSignIn);
        btnSignIn = findViewById(R.id.btnSignIn1);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("Mohon Tunggu...");
                dialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtName.getText().toString()).exists()){
                            dialog.dismiss();
                            User user = dataSnapshot.child(edtName.getText().toString()).getValue(User.class);
                            user.setName(edtName.getText().toString());
                            if (user.getPassword().equals(edtPassword.getText().toString()))
                            {
                               Intent home = new Intent(SignIn.this,Home.class);
                                Common.currentUser = user;
                                startActivity(home);
                                finish();



                            }else {
                                Toast.makeText(SignIn.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(SignIn.this, "User Name Tidak Ada di Database", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
