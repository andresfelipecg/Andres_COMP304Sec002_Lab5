package com.example.andresjose_comp304sec002_lab5_group13;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class AirplaneActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    AirplaneViewModel airplaneViewModel;
    TextView tv;
    private static String TAG = "AirplaneActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane);
        tv = findViewById(R.id.textViewDisplay
        );
        airplaneViewModel = ViewModelProviders.of(this).get(AirplaneViewModel.class);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Airplanes");

        //myRef.push().setValue("Push working now2");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                /*String value = dataSnapshot.getValue(String.class);
                tv.setText(value);
                Log.d(TAG, "Value is: " + value);*/

                HashMap<String, Object> names = (HashMap<String, Object>) dataSnapshot.getValue();
                StringBuilder output = new StringBuilder();
                if (names != null) {
                  /*  for (String person : names.keySet()) {
                        Log.d(TAG, "Key is: " + person);
                        Log.d(TAG, "Value is: " + names.get("name"));
                        String airplaneOutput="Aircraft: " + names.get(person).toString();
                        output.append(names.get(person)).append("\n");*/
                        for (Object person : names.values()) {
                       /* Log.d(TAG, "Key is: " + person);
                        Log.d(TAG, "Value is: " + names.get("name"));
                        String airplaneOutput="Aircraft: " + names.get(person).toString();*/
                        output.append("-->Airplane entry:"+person.toString()).append("\n\n");

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


    }

    public void insert(View view) {

        EditText airplane=findViewById(R.id.txtairplanename);
        EditText manufacturer=findViewById(R.id.txtmanufacturer);
        EditText cost=findViewById(R.id.txtcost);
        EditText capacity=findViewById(R.id.txtcapacity);

        Airplane a = new Airplane(airplane.getText().toString(), manufacturer.getText().toString(),
                Integer.parseInt(cost.getText().toString()), Integer.parseInt(capacity.getText().toString()));
        airplaneViewModel.insert(a);

        Toast.makeText(getApplicationContext(), "Airplane created", Toast.LENGTH_SHORT).show();

    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
           // reload();
        }
    }*/
}