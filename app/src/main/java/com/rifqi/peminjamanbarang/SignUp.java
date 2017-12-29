package com.rifqi.peminjamanbarang;

import android.app.ProgressDialog;
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
import com.rifqi.peminjamanbarang.Model.User;

public class SignUp extends AppCompatActivity {
    private Button btnSignUp;
    private MaterialEditText edtPhone,edtName,edtPassword;
    FirebaseDatabase database;
    DatabaseReference table_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btnSignUp = findViewById(R.id.btnSigUp1);
        edtPhone = findViewById(R.id.edtPhoneSignUp);
        edtName = findViewById(R.id.edtNameSignUp);
        edtPassword = findViewById(R.id.edtPasswordSignUp);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Mohon Tunggu");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtName.getText().toString()).exists())
                        {
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "User Name Sudah Terdaftar", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            User user = new User(edtPhone.getText().toString(),edtPassword.getText().toString());
                            table_user.child(edtName.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sukses", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
