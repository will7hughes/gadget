package com.whughes.gadget.db.repo;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.network.Resource;
import com.whughes.gadget.util.LiveDataUtil;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.Executor;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(AndroidJUnit4.class)
public class UserRepoLocalTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserApi userApi;
    private UserDao userDao;
    private Executor executor;

    private UserRepo userRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        userApi = Mockito.mock(UserApi.class);
        userDao = Mockito.mock(UserDao.class);
        executor = Mockito.mock(Executor.class);

        userRepo = new UserRepo(userApi, userDao, executor);
    }

    @Test
    public void fetchUser_FromSQLite() throws InterruptedException {

        MutableLiveData<UserEntity> daoResponse = new MutableLiveData<>();
        UserEntity user = new UserEntity(19, "whughes");
        daoResponse.postValue(user);

        Mockito.when(userDao.loadUserLiveData(anyString())).thenReturn(daoResponse);
        LiveData<Resource<UserEntity>> response = userRepo.fetchUser("whughes");

        Resource<UserEntity> value = LiveDataUtil.getValue(response);
        assertNotNull(value);
        assertEquals(Resource.Status.SUCCESS, value.getStatus());
        assertEquals(19, value.getData().getUserID());
    }

}