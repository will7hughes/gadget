package com.whughes.gadget.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.test.espresso.IdlingResource;


import com.whughes.gadget.R;
import com.whughes.gadget.activity.MainActivity;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.util.TestIdlingResource;
import com.whughes.gadget.util.ViewModelProviderFactory;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private LoginViewModel viewModel;

    private EditText username;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

//    @Inject
//    Drawable logo;

//    @Inject
//    RequestManager requestManager;

    @Inject
    @Named("app_user")
    UserEntity appUser;

    @Inject
    @Named("login_user")
    UserEntity loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username = findViewById(R.id.usernameTextView);
        progressBar = findViewById(R.id.loginProgressBar);

        findViewById(R.id.loginBtn).setOnClickListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(LoginViewModel.class);

//        setLogo();
//
//        subscribeObservers();
        viewModel.observeLoginState().observe(this, new Observer<Resource<UserEntity>>() {
            @Override
            public void onChanged(Resource<UserEntity> resource) {
                String message;
                if (resource != null) {
                    switch (resource.getStatus()) {
                        case LOADING:
                            message = "Loading user";
                            Log.d(TAG, message);
                            break;
                        case SUCCESS:
                            message = "Successfully loaded user: " + resource.getData().getUserID();
                            Log.d(TAG, message);
                            setIdlingResource(true);
                            onLoginSuccess();
                            break;
                        case ERROR:
                            message = "Error loading user ";
                            Log.d(TAG, message);
                            setIdlingResource(true);
                            break;
                        default:
                            message = "Error loading user. Unregistered status code: " + resource.getStatus();
                            Log.d(TAG, message);
                            break;
                    }
                }
            }
        });


        Log.d(TAG, "onCreate: " + appUser);
        Log.d(TAG, "onCreate: " + loginUser);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.loginBtn:{
                idlingResource.setIdleState(false);
                attemptLogin();
                break;
            }
        }
    }

    private void onLoginSuccess() {
        Intent menuIntent = new Intent(this, MainActivity.class);
        startActivity(menuIntent);
    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(username.getText().toString())){
            return;
        }
        viewModel.authenticateWithUsername(username.getText().toString());
    }


    // Testing only
    @Nullable
    private TestIdlingResource idlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new TestIdlingResource("TestIdlingResource");
        }
        return idlingResource;
    }

    public void setIdlingResource(boolean state) {
        if (idlingResource != null) {
            idlingResource.setIdleState(state);
        }
    }
}