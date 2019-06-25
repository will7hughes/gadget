package com.whughes.gadget.viewmodel;

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

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private final String TAG = "MAIN_VM";

    private UserRepo userRepo;

    public MutableLiveData<String> userString = new MutableLiveData<>();


    final private MutableLiveData<Request> loginRequest = new MutableLiveData<>();
    final private LiveData<Resource<UserEntity>> user = Transformations.switchMap(loginRequest, new Function<Request, LiveData<Resource<UserEntity>>>() {
        @Override
        public LiveData<Resource<UserEntity>> apply(Request input) {
            return userRepo.fetchUser(input.username);
        }
    });

    public static LoginViewModel create(FragmentActivity activity) {
        return ViewModelProviders.of(activity).get(LoginViewModel.class);
    }

    @Inject
    public void setUserRepo(UserRepo userRepo) {this.userRepo = userRepo;}

    public void login() {
        loginRequest.setValue(new Request(userString.getValue()));
    }

    public LiveData<Resource<UserEntity>> getUser() {
        return user;
    }

    public static class Request {
        private String username;
        public Request(String username) {
            this.username = username;
        }
    }
}