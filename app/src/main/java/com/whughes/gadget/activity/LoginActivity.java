package com.whughes.gadget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.test.espresso.IdlingResource;

import com.whughes.gadget.App;
import com.whughes.gadget.R;
import com.whughes.gadget.databinding.LoginActivityBinding;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.util.Constants;
import com.whughes.gadget.util.Injector;
import com.whughes.gadget.util.ServiceIdlingResource;
import com.whughes.gadget.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private final String TAG = "LOGIN_ACTIVITY";

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        LoginActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.login_activity);
        binding.setLifecycleOwner(this);
        loginViewModel = LoginViewModel.create(this);
        binding.setLoginViewModel(loginViewModel);

        new Injector().inject(loginViewModel);
//        App.getAppComponent().inject(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<Resource<UserEntity>>() {
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
                            message = "Successfully loaded user";
                            Log.d(TAG, message);

                            setIdlingResource(true);
                            Intent menuIntent = new Intent(LoginActivity.this, MainActivity.class);
                            menuIntent.putExtra(Constants.USER_ID, resource.getData().getUserID());
                            startActivity(menuIntent);

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
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {

            setIdlingResource(false);
            loginViewModel.login();
        }
    }


    // Testing only
    @Nullable
    private ServiceIdlingResource idlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new ServiceIdlingResource("ServiceIdlingResource");
        }
        return idlingResource;
    }

    public void setIdlingResource(boolean state) {
        if (idlingResource != null) {
            idlingResource.setIdleState(state);
        }
    }
}
