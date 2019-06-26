package com.whughes.gadget.api;

import androidx.lifecycle.LiveData;

import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.ApiResponse;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("UserIndex/{username}")
    LiveData<ApiResponse<UserEntity>> getLiveDataUser(@Path("username") String username);

}