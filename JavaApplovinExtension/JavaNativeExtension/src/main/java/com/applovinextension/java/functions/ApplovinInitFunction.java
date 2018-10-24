package com.applovinextension.java.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovin.sdk.AppLovinSdk;

public class ApplovinInitFunction implements FREFunction {


    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            AppLovinSdk.initializeSdk(freContext.getActivity());
        } catch (Exception e) {
            try {
                return FREObject.newObject(e.getMessage());
            } catch (Exception e1) {
                return null;
            }
        }
        return null;
    }
}
