package com.harshilyadav.eatit;

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
import com.harshilyadav.eatit.Models.User;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText ePh,eNa,ePa;
    Button signUp;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        eNa=(MaterialEditText)findViewById(R.id.edtName);
        ePh=(MaterialEditText)findViewById(R.id.edtPhone);
        ePa=(MaterialEditText)findViewById(R.id.edtPassword);

        signUp=(Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog=new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(ePh.getText().toString()).exists()&& flag==false)
                        {
                            mDialog.dismiss();
                            Toast.makeText(SignUp.this,"Phone Number already exists !!",Toast.LENGTH_SHORT).show();
                        }
                        else if(flag==false)
                        {
                            mDialog.dismiss();
                            User user=new User(eNa.getText().toString(),ePa.getText().toString());
                            table_user.child(ePh.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign Up succesfull  !!",Toast.LENGTH_SHORT).show();
                            flag=true;
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
