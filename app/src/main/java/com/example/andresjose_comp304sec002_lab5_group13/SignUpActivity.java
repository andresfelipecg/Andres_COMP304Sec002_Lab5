package com.example.andresjose_comp304sec002_lab5_group13;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);



    }
    public void singUp(View view){
        EditText email=findViewById(R.id.user_id2);
        EditText pass=findViewById(R.id.password_id2);
        EditText confirmPass=findViewById(R.id.passwordConfirm_id2);

        if(!pass.getText().toString().equals(confirmPass.getText().toString())){
            Toast.makeText(SignUpActivity.this, "Password and confirmation failure", Toast.LENGTH_SHORT).show();
        }
        else{
        userViewModel.signUp(email.getText().toString(),pass.getText().toString());

        userViewModel.getSignUpResult().observe(this, result -> {
            if(result){
                Toast.makeText(SignUpActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(SignUpActivity.this, "Signed Up Failed", Toast.LENGTH_SHORT).show();

            }
        });
        }
    }



    public void goToLoginPage(View view) {
        Intent i=new Intent(SignUpActivity.this,MainActivity.class);
        startActivity(i);
    }
}