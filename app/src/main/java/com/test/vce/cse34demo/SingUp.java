package com.test.vce.cse34demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.test.vce.cse34demo.Home;
import com.test.vce.cse34demo.MainActivity;
import com.test.vce.cse34demo.R;

public class SingUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ProgressDialog progressDialog;
    EditText user;
    EditText pass;
    Button SingUp;
    TextView ha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        RelativeLayout your_Layout = (RelativeLayout) findViewById(R.id.signuplayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) your_Layout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
        user=(EditText)findViewById(R.id.emails);
        pass=(EditText)findViewById(R.id.passs);
        SingUp=(Button)findViewById(R.id.logins);
        ha=(TextView)findViewById(R.id.alreadys);
        progressDialog=new ProgressDialog(SingUp.this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(SingUp.this,Home.class));
                }
            }
        };

        ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingUp.this,MainActivity.class));
            }
        });

        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u=user.getText().toString().trim();
                String p=pass.getText().toString().trim();
                if(TextUtils.isEmpty(u))
                {
                    Toast.makeText(SingUp.this,"Please enter a valid email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(p))
                {
                    Toast.makeText(SingUp.this,"Please enter a valid password",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    progressDialog.setMessage("Hold for a sec...");
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(u, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(SingUp.this,Home.class));
                            } else {
                                Toast.makeText(SingUp.this, "Invalid data", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                    });
                }
            }
        });
    }
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SingUp.this,MainActivity.class));
    }
}
