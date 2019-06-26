package com.whughes.gadget;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;


import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.ui.login.LoginActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "DaggerExample";

    @Inject
    public SessionManager sessionManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers(){
        sessionManager.getUser().observe(this, new Observer<Resource<UserEntity>>() {
            @Override
            public void onChanged(Resource<UserEntity> userLoginResource) {
                if(userLoginResource != null){
                    switch (userLoginResource.getStatus()){
                        case LOADING:{
                            Log.d(TAG, "onChanged: BaseActivity: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            Log.d(TAG, "onChanged: BaseActivity: SUCCESS... " +
                                    "Authenticated as: " + userLoginResource.getData().getUserID());
                            break;
                        }

                        case ERROR:{
                            Log.d(TAG, "onChanged: BaseActivity: ERROR...");
                            break;
                        }
                    }
                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}















