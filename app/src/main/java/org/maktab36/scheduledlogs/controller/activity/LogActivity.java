package org.maktab36.scheduledlogs.controller.activity;

import androidx.fragment.app.Fragment;

import org.maktab36.scheduledlogs.controller.fragment.LogFragment;

public class LogActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return LogFragment.newInstance();
    }
}