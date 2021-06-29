
package com.example.lapcov_19.ui;

import android.app.FragmentTransaction;

import com.example.lapcov_19.R;
import com.example.lapcov_19.ui.logger.LoggerActivity;


public class BaseActivity extends LoggerActivity
{
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void addLogFragment() {
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final LogFragment fragment = new LogFragment();
        transaction.replace(R.id.framelog, fragment);
        transaction.commit();
    }
}
