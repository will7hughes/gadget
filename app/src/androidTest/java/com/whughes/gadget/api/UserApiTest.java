package com.whughes.gadget.api;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.ApiResponse;
import com.whughes.gadget.util.LiveDataUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import retrofit2.Response;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class UserApiTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserApi userApi;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        userApi = Mockito.mock(UserApi.class);
    }

    @Test
    public void getLiveDataUserTest() throws InterruptedException {
        UserEntity userEntity = new UserEntity(19, "whughes");
        Response<UserEntity> resp = Response.success(200, userEntity);
        ApiResponse<UserEntity> apiResponse = new ApiResponse<>(resp);

        MutableLiveData<ApiResponse<UserEntity>> response = new MutableLiveData<>();
        response.setValue(apiResponse);

        when(userApi.getLiveDataUser("whughes")).thenReturn(response);
        userApi.getLiveDataUser("whughes");

        ApiResponse<UserEntity> data = LiveDataUtil.getValue(response);
        assertNotNull(data);
        assertEquals(userEntity, data.getBody());
    }
}
