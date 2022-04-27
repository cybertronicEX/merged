package com.example.libraysystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText userNameEdt, pwdEdt, cnfPwEdt;
    private Button registerBtn;
    private ProgressBar loadingPb;
    private TextView loginTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userNameEdt = findViewById(R.id.idEditUsername);
        pwdEdt = findViewById(R.id.idEditPwd);
        cnfPwEdt = findViewById(R.id.idedtCnfPwd);
        registerBtn = findViewById(R.id.idBtnRegister);
        loadingPb = findViewById(R.id.idPBLoading);
        loginTV = findViewById(R.id.idTVLogin);
        mAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
                Intent i = new Intent(RegistrationActivity.this,loginActivity.class);
                startActivity(i);
           }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPb.setVisibility(View.VISIBLE);
                String userName = userNameEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                String cnfPwd = cnfPwEdt.getText().toString();
                if(!pwd.equals(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "both pw should be same",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)){
                    Toast.makeText(RegistrationActivity.this, "why the empty??",Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPb.setVisibility(View.GONE);
                                Toast.makeText(RegistrationActivity.this, "User registered..",Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                }
            }
        });
    }
}