package com.lawrene.falcon.aniekeme;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText mEmail;
    EditText mPassword;
    Button mLoginBtn;
    ProgressDialog mProgressDialog;
    TextView mGotoreg;
    FirebaseAuth mFireAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFireAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);


        mEmail = (EditText) findViewById(R.id.email_login);
        mPassword = (EditText) findViewById(R.id.passw_login);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
        mGotoreg = (TextView) findViewById(R.id.go_to_reg);

        mEmail.setText("a@gmail.com");
        mPassword.setText("aaaaaaaa");

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInuser(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });

        mGotoreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    public void signInuser(String email, String password){
        mProgressDialog.setTitle("Please wait");
        mProgressDialog.setMessage("Loggin you in");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        mFireAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                mProgressDialog.dismiss();
                finish();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        System.exit(0);
    }
}
