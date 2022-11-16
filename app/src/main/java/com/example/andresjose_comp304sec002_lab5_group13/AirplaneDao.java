package com.example.andresjose_comp304sec002_lab5_group13;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AirplaneDao {
    private static volatile AirplaneDao INSTANCE;
    private static final String DATABASE_NAME = "Airplanes";
    FirebaseDatabase database;
    DatabaseReference myRef;

    //Singleton Pattern to have one instance of DB

    private AirplaneDao(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(DATABASE_NAME);
    }

    public static AirplaneDao getInstance(){
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = new AirplaneDao();
        }
        return INSTANCE;
    }

    public void insert(Airplane airplane){
        myRef.push().setValue(airplane);
    }

}
