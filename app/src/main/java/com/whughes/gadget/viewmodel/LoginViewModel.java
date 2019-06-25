package com.whughes.gadget.viewmodel;

import android.util.Log;

import androidx.annotation.VisibleForTesting;
import androidx.arch.core.util.Function;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.db.repo.UserRepo;
import com.whughes.gadget.network.Resource;

import java.util.Objects;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {

    public static final String TAG = "LOGIN_VM";
    public MutableLiveData<String> userString = new MutableLiveData<>();

    @SuppressWarnings("unchecked")
    @Inject
    public LoginViewModel(UserRepo userRepo) {
        Log.d(TAG, "LoginViewModel: ");
    }
}