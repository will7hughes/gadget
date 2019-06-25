/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.whughes.gadget.util;


import androidx.fragment.app.FragmentManager;

import com.whughes.gadget.R;
import com.whughes.gadget.activity.MainActivity;
import com.whughes.gadget.fragment.LoginFragment;

import javax.inject.Inject;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */
public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;
    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToLogin() {
        LoginFragment loginFragment = LoginFragment.create();
        fragmentManager.beginTransaction()
                .replace(containerId, loginFragment, "login")
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}
