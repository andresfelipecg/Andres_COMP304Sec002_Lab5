package com.example.andresjose_comp304sec002_lab5_group13;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    TextView tv;
    AirplaneViewModel airplaneViewModel;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.TextView);

        airplaneViewModel = ViewModelProviders.of(this).get(AirplaneViewModel.class);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        Button signUpButton=findViewById(R.id.singUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(MainActivity.this, SignUpActivity.class);
                        startActivity(i);
                    }
            });

        Button loginButton=findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText email=findViewById(R.id.user_id);
                EditText pass=findViewById(R.id.password_id);
                userViewModel.login(email.getText().toString(),pass.getText().toString());


                userViewModel.getLoginResult().observe(MainActivity.this, result -> {
                    if(result){
                        Toast.makeText(MainActivity.this, "login  Successfully", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this, AirplaneActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(MainActivity.this, "login Failed", Toast.LENGTH_SHORT).show();

                    }

                });

            }

        });

                /*Airplane a = new Airplane("767", "Boeing", 19000000, 200);
        airplaneViewModel.insert(a);*/

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.push().setValue("Push working now2");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                *//*String value = dataSnapshot.getValue(String.class);
                tv.setText(value);
                Log.d(TAG, "Value is: " + value);*//*

                HashMap<String, String> names = (HashMap<String, String>) dataSnapshot.getValue();
                StringBuilder output = new StringBuilder();
                if (names != null) {
                    for (String person : names.keySet()) {
                        Log.d(TAG, "Key is: " + person);
                        Log.d(TAG, "Value is: " + names.get(person));
                        output.append(names.get(person)).append("\n");
                    }
                }
                tv.setText(output.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
*/

    }
}