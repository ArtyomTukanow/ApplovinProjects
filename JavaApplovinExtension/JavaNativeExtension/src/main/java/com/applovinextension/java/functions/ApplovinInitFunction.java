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
            AppLovinSdk.initializeSdk(freContext.getActivity());
            //preload reward ad
            RewardedAdFunction.init(freContext.getActivity().getApplicationContext());
        } catch (Exception e) {
            Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());
        }
        return null;
    }
}
