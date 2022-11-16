package com.example.andresjose_comp304sec002_lab5_group13;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserDao {
    private static volatile UserDao INSTANCE;
    private static final String DATABASE_NAME = "Users";
    private static final String TAG="DAO";
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    MutableLiveData<Boolean> flagSignUp =new MutableLiveData<>();
    MutableLiveData<Boolean> flagLogin =new MutableLiveData<>();
    //Singleton Pattern to have one instance of DB

    private UserDao(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(DATABASE_NAME);
        mAuth=FirebaseAuth.getInstance();
    }

    public static UserDao getInstance(){
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    public void insert(User user){
        myRef.push().setValue(user);
    }

    public FirebaseUser getStatus(){
        currentUser=mAuth.getCurrentUser();
        if(currentUser !=null){
            //action. Do something
            Log.d(TAG,"Current User "+currentUser.getDisplayName());
        }
        return currentUser;
    }

    private void signUpSuccess(boolean signup){
        flagSignUp.postValue(signup);
    }
    private void loginSuccess(boolean login){
        flagLogin.postValue(login);
    }
    public  LiveData<Boolean> getSignUpResult(){return flagSignUp;}
    public  LiveData<Boolean> getLoginResult(){return flagLogin;}

    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"CreateUserWithEmail:success");
                    currentUser=mAuth.getCurrentUser();
                    Log.d(TAG,"Current User "+currentUser.getEmail());
                    signUpSuccess(true);
                }else{
                    //if sign fail
                    Log.w(TAG,"CreateUserWithEmail:failure",task.getException());

                    signUpSuccess(false);
                }
            }
        });

       /* mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()){

             }
            }
        });*/
    }
    public void login(String email, String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG,"signIn:success");
                    currentUser=mAuth.getCurrentUser();
                    Log.d(TAG,"Current User "+currentUser.getEmail());
                    Log.d(TAG,"User UID "+currentUser.getUid());
                    loginSuccess(true);
                }else{
                    //if sign fail
                    Log.w(TAG,"signIn:failure",task.getException());
                    loginSuccess(false);
                }
            }
        });
    }

}
