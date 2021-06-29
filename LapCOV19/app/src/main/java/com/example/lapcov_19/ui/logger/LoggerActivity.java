
package com.example.lapcov_19.ui.logger;

import android.app.Activity;

import com.example.lapcov_19.R;
import com.example.lapcov_19.ui.LogFragment;


public class LoggerActivity extends Activity {

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        initializeLogging();
    }

    private void initializeLogging() {
        LogFragment logFragment = (LogFragment) getFragmentManager().findFragmentById(R.id.framelog);

        LogCatWrapper logcat = new LogCatWrapper();
        logcat.setNext(logFragment.getLogView());

        LocationLog.setLogNode(logcat);
    }
}
