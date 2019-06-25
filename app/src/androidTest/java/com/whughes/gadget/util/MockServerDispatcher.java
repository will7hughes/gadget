package com.whughes.gadget.util;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;


import com.whughes.gadget.R;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

public class MockServerDispatcher {
    public static final String TAG = "DISPATCH";
    private Context targetContext;
    private boolean isOnline;
    public MockServerDispatcher(boolean isOnline) {
        this.targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        this.isOnline = isOnline;
    }

    public class RequestDispatcher extends Dispatcher {
        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
            if (isOnline) {
                if (request.getPath().equals("UserIndex")) {
//                    String userListResponse = new JSONConverter(R.raw.user_list).getString(targetContext);
//                    return new MockResponse().setResponseCode(200).setBody(userListResponse);
                }
                return new MockResponse().setResponseCode(404);
            } else {
                return new MockResponse().setResponseCode(404);
            }
        }
    }

    class ErrorDispatcher extends Dispatcher {
        @Override
        public MockResponse dispatch(RecordedRequest request) {

            return new MockResponse().setResponseCode(400);
        }
    }

}
