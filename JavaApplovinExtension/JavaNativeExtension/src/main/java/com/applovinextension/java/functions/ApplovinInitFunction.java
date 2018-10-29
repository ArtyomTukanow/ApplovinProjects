package com.applovinextension.java.functions;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovin.sdk.AppLovinSdk;
import com.applovinextension.java.ApplovinExtension;

public class ApplovinInitFunction implements FREFunction {


    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            AppLovinSdk.initializeSdk(freContext.getActivity().getApplicationContext());
            //preload reward ad
            RewardedAdFunction.setContext(freContext.getActivity().getApplicationContext());
            RewardedAdFunction.init();
        } catch (Exception e) {
            Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());
        }
        return null;
    }
}
