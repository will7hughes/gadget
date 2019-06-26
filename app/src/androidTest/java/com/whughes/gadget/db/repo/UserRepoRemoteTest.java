package com.whughes.gadget.db.repo;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.whughes.gadget.R;
import com.whughes.gadget.api.UserApi;
import com.whughes.gadget.db.dao.UserDao;
import com.whughes.gadget.ui.login.LoginActivity;
import com.whughes.gadget.util.MockServerDispatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.concurrent.Executor;

import okhttp3.mockwebserver.MockWebServer;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UserRepoRemoteTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class, false, false);

//    @Rule
//    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private UserApi userApi;
    private UserDao userDao;
    private Executor executor;

    private UserRepo userRepo;

    private IdlingResource idlingResource;

    private MockWebServer server;

    @Before
    public void init() throws IOException {
        MockitoAnnotations.initMocks(this);

        userApi = Mockito.mock(UserApi.class);
        userDao = Mockito.mock(UserDao.class);
        executor = Mockito.mock(Executor.class);

        userRepo = new UserRepo(userApi, userDao, executor);

        server = new MockWebServer();
        server.start(8080);
        server.setDispatcher(new MockServerDispatcher(true).new RequestDispatcher());

        ActivityScenario activityScenario = ActivityScenario.launch(LoginActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                idlingResource = activity.getIdlingResource();
                IdlingRegistry.getInstance().register(idlingResource);
            }
        });
    }

    @After
    public void shutdownAndReset() throws IOException {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
        server.shutdown();
    }


    @Test
    public void fetchUser_FromSQLServer() {
        onView(withId(R.id.usernameTextView)).perform(typeText("whughes"));
        onView(withId(R.id.loginBtn)).perform(click());
    }
}
