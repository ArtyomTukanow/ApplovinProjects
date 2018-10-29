package com.applovinextension.java.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.applovinextension.java.ApplovinExtension;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(ApplovinExtension.LOG_TYPE, "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.e(ApplovinExtension.LOG_TYPE, "onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(ApplovinExtension.LOG_TYPE, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e(ApplovinExtension.LOG_TYPE, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(ApplovinExtension.LOG_TYPE, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(ApplovinExtension.LOG_TYPE, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(ApplovinExtension.LOG_TYPE, "onDestroy");
        super.onDestroy();
    }
}
