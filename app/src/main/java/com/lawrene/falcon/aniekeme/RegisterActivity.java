package com.lawrene.falcon.aniekeme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    String email, password;
    EditText mEmailEdt, mPasswordEdt, mDisplayNameEdt;
    TextView gotologin;
    Button mRegBtn;
    FirebaseAuth mFireAuth;
    DatabaseReference mUserDatabase;
    FirebaseUser mCurrentUser;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailEdt = findViewById(R.id.email_reg);
        mPasswordEdt = findViewById(R.id.passw_reg);
        mDisplayNameEdt = findViewById(R.id.username_reg);
        gotologin = findViewById(R.id.go_to_login);
        mRegBtn = findViewById(R.id.create_account_btn);
        mFireAuth = FirebaseAuth.getInstance();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mCurrentUser = mFireAuth.getCurrentUser();
        mProgressDialog = new ProgressDialog(this);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setTitle("Please wait");
                mProgressDialog.setMessage("Creating your account");
                registernewuser(mEmailEdt.getText().toString(), mPasswordEdt.getText().toString(), mDisplayNameEdt.getText().toString());
            }
        });

        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void registernewuser(String email, String password, final String username) {
        if (email.equals("") || password.equals("") || username.equals("")) {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
        else if(!email.contains("@")){
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show();
        }
        else{
            mProgressDialog.show();
            final HashMap<String, String> userMap = new HashMap<>();
            userMap.put("username",username );
            userMap.put("department", "Computer Engineering");


            mFireAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    mUserDatabase.child(mDisplayNameEdt.getText().toString()).setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(RegisterActivity.this, "Your account has been created", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
