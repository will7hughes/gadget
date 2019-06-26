package com.whughes.gadget;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;


import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.AppDatabase;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.ApiResponse;
import com.whughes.gadget.network.NetworkBoundResource;
import com.whughes.gadget.network.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";


    private UserApi api; // @AuthScope scoped dependency
    private UserDao dao;

    @Inject
    public void initAccessors(AppDatabase appDatabase, Retrofit retrofit) {
        this.api = retrofit.create(UserApi.class);
        this.dao = appDatabase.userDao();
    }

    @Inject
    public SessionManager(){}

    final private MutableLiveData<UserRequest> loginRequest = new MutableLiveData<>();
    final private LiveData<Resource<UserEntity>> user = Transformations.switchMap(loginRequest, new Function<UserRequest, LiveData<Resource<UserEntity>>>() {
        @Override
        public LiveData<Resource<UserEntity>> apply(final UserRequest input) {
            return new NetworkBoundResource<UserEntity, UserEntity>() {

                @Override
                protected void saveCallResult(@NonNull UserEntity item) {
                    dao.insert(item);
                }

                @Override
                protected boolean shouldFetch(@Nullable UserEntity data) {
                    if (data == null) {
                        Log.d(TAG, "shouldFetch: true");
                        return true;
                    } else {
                        Log.d(TAG, "shouldFetch: false");
                        return false;
                    }
                }

                @NonNull
                @Override
                protected LiveData<UserEntity> loadFromDb() {
                    return dao.loadUserLiveData(input.username);
                }

                @NonNull
                @Override
                protected LiveData<ApiResponse<UserEntity>> createCall() {
                    return api.getLiveDataUser(input.username);
                }
            }.getAsLiveData();
        }
    });
    public LiveData<Resource<UserEntity>> getUser() {
        return user;
    }

    public void authenticateWithUsername(final String username) {
        loginRequest.setValue(new UserRequest(username));
    }


    public static class UserRequest {
        private String username;
        public UserRequest(String username) {
            this.username = username;
        }
    }
}
















