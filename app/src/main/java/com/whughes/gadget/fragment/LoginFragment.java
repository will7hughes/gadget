package com.whughes.gadget.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.whughes.gadget.R;
import com.whughes.gadget.databinding.LoginFragmentBinding;
import com.whughes.gadget.di.Injectable;
import com.whughes.gadget.util.NavigationController;
import com.whughes.gadget.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginFragment extends Fragment implements Injectable {
    private static final String LOGIN_KEY = "login";

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    NavigationController navigationController;

    private LoginViewModel loginViewModel;


    public static LoginFragment create() {
        LoginFragment userFragment = new LoginFragment();
        return userFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        LoginFragmentBinding dataBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.login_fragment,
                container,
                false);

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

    }

}
