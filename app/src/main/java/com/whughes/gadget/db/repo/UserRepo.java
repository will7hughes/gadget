package com.whughes.gadget.db.repo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;


import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.ApiResponse;
import com.whughes.gadget.network.NetworkBoundResource;
import com.whughes.gadget.network.Resource;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class UserRepo {
    public static final String TAG = "USER_REPO";

    final private UserApi api;
    final private UserDao dao;
    final private Executor executor;

    @Inject
    public UserRepo(UserApi api, UserDao dao, Executor executor) {
        this.api = api;
        this.dao = dao;
        this.executor = executor;
    }

    public LiveData<Resource<UserEntity>> fetchUser(final String username) {
        return new NetworkBoundResource<UserEntity, UserEntity>() {

            @Override
            protected void saveCallResult(@NonNull UserEntity item) {
                dao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable UserEntity data) {
                if (data == null) {
                    return true;
                } else {
                    return false;
                }
            }

            @NonNull
            @Override
            protected LiveData<UserEntity> loadFromDb() {
                return dao.loadUserLiveData(username);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserEntity>> createCall() {
                return api.getLiveDataUser(username);
            }
        }.getAsLiveData();
    }
}