package com.example.helperdeneme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

//Ezel Karadirek
public class Register extends AppCompatActivity {
    MaterialEditText editNameSurname, editEmail, editPassword, editConfirmPassword, editSsn, editPhone;
    Button btnRegister;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editNameSurname = findViewById(R.id.txtRegisterName);
        editEmail = findViewById(R.id.txtRegisterEmail);
        editPassword = findViewById(R.id.txtRegisterPassword);
        editConfirmPassword = findViewById(R.id.txtRegisterConfirmPassword);
        editSsn = findViewById(R.id.txtRegisterSsn);
        editPhone = findViewById(R.id.txtRegisterPhone);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth= FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtNameSurname = editNameSurname.getText().toString();
                String txtEmail = editEmail.getText().toString().trim();
                String txtPassword = editPassword.getText().toString().trim();
                String txtSsn = editSsn.getText().toString();
                String txtPhone = editPhone.getText().toString();
                String txtConfirmPassword = editConfirmPassword.getText().toString().trim();

                //These checks necessary places are full or empty, password length and 2 passwords are same
                if (TextUtils.isEmpty(txtNameSurname) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtPhone) || TextUtils.isEmpty(txtConfirmPassword)){
                    Toast.makeText(Register.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
                }else if(txtPassword.length() < 8){
                    Toast.makeText(Register.this, "Password must be at least 8 characters!", Toast.LENGTH_SHORT).show();
                }else{
                    register(txtNameSurname,  txtEmail, txtPassword, txtPhone, txtSsn);
                }
            }
        });
    }
    private void register(String nameSurname, String email, String password, String phone, String ssn){
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){ //if firebase can get informations task is true
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userId = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("users").child(userId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userId);
                            hashMap.put("Name Surname", nameSurname);
                            hashMap.put("Mail", email);
                            hashMap.put("Password", password);
                            hashMap.put("Ssn", ssn);
                            hashMap.put("Phone", phone);

                            // If registration process OK, redirect the user to login activity.
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        //after register process return to login page and login
                                        Intent intent = new Intent(Register.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else{
                            task.getException().printStackTrace();
                            Toast.makeText(Register.this, "You can't register with this email or password. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}