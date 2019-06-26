package com.whughes.gadget.viewmodel;

import android.content.res.Resources;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;


import com.whughes.gadget.SessionManager;
import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.AppDatabase;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.db.repo.UserRepo;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.network.util.LiveDataCallAdapterFactory;
import com.whughes.gadget.ui.login.LoginViewModel;
import com.whughes.gadget.util.Constants;
import com.whughes.gadget.util.LiveDataUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private LoginViewModel loginViewModel;
    private UserApi api;
    private UserDao dao;
    private SessionManager sessionManager;
//    private UserRepo userRepo;

    @Inject
    AppDatabase appDatabase;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        userRepo = Mockito.mock(UserRepo.class);
        api = Mockito.mock(UserApi.class);
        dao = Mockito.mock(UserDao.class);
        sessionManager = new SessionManager();
        sessionManager.initAccessors(appDatabase, retrofit);

        loginViewModel = new LoginViewModel(api, dao, sessionManager);
//        loginViewModel.setUserRepo(userRepo);
    }

    @Test
    public void loginTest() throws Exception {
        UserEntity responseData = new UserEntity(19, "whughes");
        Resource<UserEntity> resource = Resource.success(responseData);
        MutableLiveData<Resource<UserEntity>> resp = new MutableLiveData<>();
        resp.setValue(resource);

//        when(userRepo.fetchUser("whughes")).thenReturn(resp);

//        loginViewModel.userString.setValue("whughes");
//        loginViewModel.login();
        loginViewModel.authenticateWithUsername("whughes");

        Resource<UserEntity> data = LiveDataUtil.getValue(sessionManager.getUser());

        Assert.assertEquals(Resource.Status.SUCCESS, data.getStatus());
        Assert.assertEquals(19, data.getData().getUserID());
    }

    @Test
    public void invalidLoginTest() throws Exception {
        Resource<UserEntity> resource = Resource.error(new Resources.NotFoundException(), null);
        MutableLiveData<Resource<UserEntity>> resp = new MutableLiveData<>();
        resp.setValue(resource);

//        when(userRepo.fetchUser("nobody")).thenReturn(resp);
//
//        loginViewModel.userString.setValue("nobody");
//        loginViewModel.login();
        loginViewModel.authenticateWithUsername("nobody");


        Resource<UserEntity> data = LiveDataUtil.getValue(sessionManager.getUser());
        Assert.assertEquals(Resource.Status.ERROR, data.getStatus());
        assertNull(data.getData());
    }
}
