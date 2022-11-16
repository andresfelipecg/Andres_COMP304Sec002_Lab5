package com.example.andresjose_comp304sec002_lab5_group13;

import android.content.Context;

public class AirplaneRepository {

    private final AirplaneDao airplaneDao;

    public AirplaneRepository(Context context) {
        airplaneDao = AirplaneDao.getInstance();
    }

    public void insert(Airplane airplane){
        airplaneDao.insert(airplane);
    }
}
