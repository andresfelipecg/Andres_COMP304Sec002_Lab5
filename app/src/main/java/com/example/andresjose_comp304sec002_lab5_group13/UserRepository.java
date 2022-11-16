package com.example.andresjose_comp304sec002_lab5_group13;

import android.content.Context;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private final UserDao userDao;
    private LiveData<Boolean> signup;
    private LiveData<Boolean> login;

    public UserRepository(Context context) {

        userDao = UserDao.getInstance();
        signup=  userDao.getSignUpResult();
        login=  userDao.getLoginResult();
    }

    public void insert(User user){

        userDao.insert(user);
    }
    public void signUp(String email, String password){userDao.signUp(email,password);}
    public void login(String email, String password){userDao.login(email,password);}

    //Return insert result
    public LiveData<Boolean> getSignUpResult(){return signup;}
    public LiveData<Boolean> getLoginResult(){return login;}
}
