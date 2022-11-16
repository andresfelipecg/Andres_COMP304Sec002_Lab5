package com.example.andresjose_comp304sec002_lab5_group13;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AirplaneViewModel extends AndroidViewModel {
    private AirplaneRepository airplaneRepository;

    public AirplaneViewModel(@NonNull Application application) {
        super(application);
        airplaneRepository = new AirplaneRepository(application);
    }

    public void insert(Airplane airplane){
        airplaneRepository.insert(airplane);
    }
}
