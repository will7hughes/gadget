package com.whughes.gadget.ui.login;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import com.whughes.gadget.SessionManager;
import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.Resource;

import java.util.List;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";

    // inject
    private final SessionManager sessionManager; // @Singleton scoped dependency
    private final UserApi userApi; // @AuthScope scoped dependency
    private final UserDao userDao;



    @Inject
    public LoginViewModel(UserApi userApi, UserDao userDao, SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.userApi = userApi;
        this.userDao = userDao;
        Log.d(TAG, "LoginViewModel: viewmodel is working...");
    }


    public LiveData<Resource<UserEntity>> observeLoginState() {return sessionManager.getUser();}


//    LiveData<AuthResource<UserEntity>>
    public void authenticateWithUsername(String username) {
        Log.d(TAG, "authenticateWithUsername: " + username);
        sessionManager.authenticateWithUsername(username);
    }

}