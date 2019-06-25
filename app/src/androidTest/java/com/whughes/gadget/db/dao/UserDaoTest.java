package com.whughes.gadget.db.dao;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.whughes.gadget.db.AppDatabase;
import com.whughes.gadget.db.entity.UserEntity;
import com.whughes.gadget.util.LiveDataUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    // Swaps background executor used by architecture components
    // with a different one which executes each task synchronously
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    private UserDao userDao;
    private AppDatabase appDatabase;

    @Before
    public void createSQLiteDb() {
        Context context = InstrumentationRegistry.getInstrumentation().getContext();

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();
        userDao = appDatabase.userDao();
    }

    @After
    public void closeSQLiteDb() {
        appDatabase.close();
    }

    @Test
    public void loadUserLiveData() throws InterruptedException {
        UserEntity whughes = new UserEntity(19, "whughes");
        userDao.insert(whughes);

        LiveData<UserEntity> user = userDao.loadUserLiveData("whughes");
        assertNotNull(user);

        assertEquals(whughes.getUserID(), LiveDataUtil.getValue(user).getUserID());
    }










//
//
//    @Test
//    public void insertUserTest() {
//        String username = "jdoe";
//        UserEntity jane = new UserEntity(1, username);
//        userDao.insert(jane);
//        UserEntity jDoe = userDao.loadUser(username);
//        assertEquals("SQLite Database insert and get with username", username, jDoe.getUsername());
//    }
//
//    @Test
//    public void insertAllThenLoadAllUsersTest() {
//        UserEntity jane = new UserEntity(1, "jdoe");
//        UserEntity person =  new UserEntity(2, "aperson");
//
//        UserEntity[] users = new UserEntity[]{jane, person};
//        userDao.insertAll(users);
//
//        List<UserEntity> loadedUsers = userDao.loadAll();
//
//        assertEquals("SQLite Database insert all and load users", loadedUsers.get(0).getUsername(), jane.getUsername());
//        assertEquals("SQLite Database insert all and load users", loadedUsers.get(1).getUsername(), person.getUsername());
//    }
//
//    @Test
//    public void deleteAllUsersTest() {
//
//        UserEntity jane = new UserEntity(1, "jdoe");
//        UserEntity person =  new UserEntity(2, "aperson");
//
//        UserEntity[] users = new UserEntity[]{jane, person};
//        userDao.insertAll(users);
//
//        userDao.deleteAll();
//        List<UserEntity> loadedUsers = userDao.loadAll();
//
//        assertTrue("SQLite Database delete all users", loadedUsers.isEmpty());
//    }
//
//    @Test
//    public void loadUserTest() {
//        String username = "jdoe";
//
//        UserEntity jane = new UserEntity(1, username);
//        UserEntity person =  new UserEntity(2, "aperson");
//
//        UserEntity[] users = new UserEntity[]{jane, person};
//        userDao.insertAll(users);
//
//        UserEntity loadedUser = userDao.loadUser(username);
//
//
//        assertEquals("SQLite Database load user", username, loadedUser.getUsername());
//    }

}
