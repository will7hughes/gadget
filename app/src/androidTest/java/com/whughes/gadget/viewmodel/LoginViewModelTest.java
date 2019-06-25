package com.whughes.gadget.viewmodel;

import android.content.res.Resources;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;


import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.db.repo.UserRepo;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.util.LiveDataUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class LoginViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private LoginViewModel loginViewModel;
    private UserRepo userRepo;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        userRepo = Mockito.mock(UserRepo.class);

        loginViewModel = new LoginViewModel();
        loginViewModel.setUserRepo(userRepo);
    }

    @Test
    public void loginTest() throws Exception {
        UserEntity responseData = new UserEntity(19, "whughes");
        Resource<UserEntity> resource = Resource.success(responseData);
        MutableLiveData<Resource<UserEntity>> resp = new MutableLiveData<>();
        resp.setValue(resource);

        when(userRepo.fetchUser("whughes")).thenReturn(resp);

        loginViewModel.userString.setValue("whughes");
        loginViewModel.login();

        Resource<UserEntity> data = LiveDataUtil.getValue(loginViewModel.getUser());

        Assert.assertEquals(Resource.Status.SUCCESS, data.getStatus());
        Assert.assertEquals(19, data.getData().getUserID());
    }

    @Test
    public void invalidLoginTest() throws Exception {
        Resource<UserEntity> resource = Resource.error(new Resources.NotFoundException(), null);
        MutableLiveData<Resource<UserEntity>> resp = new MutableLiveData<>();
        resp.setValue(resource);

        when(userRepo.fetchUser("nobody")).thenReturn(resp);

        loginViewModel.userString.setValue("nobody");
        loginViewModel.login();

        Resource<UserEntity> data = LiveDataUtil.getValue(loginViewModel.getUser());
        Assert.assertEquals(Resource.Status.ERROR, data.getStatus());
        assertNull(data.getData());
    }
}
