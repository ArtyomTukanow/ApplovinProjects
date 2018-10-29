package com.applovinextension.java.functions;

import android.content.Context;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovinextension.java.ApplovinExtension;

public class RewardedAdFunction implements FREFunction {

    private static Context mContext;
    public static AppLovinIncentivizedInterstitial myIncent;

    public static void init() {
        myIncent.preload(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd appLovinAd) {
                Log.e(ApplovinExtension.LOG_TYPE, "Reward add preloaded!");

            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                Log.e(ApplovinExtension.LOG_TYPE, "Failed to preload reward add!");
            }
        });
    }

    public static void setContext(Context context) {
        if (context == mContext)
            return;

        mContext = context;
        myIncent = AppLovinIncentivizedInterstitial.create(context);
    }

    private static Context getContext() {
        return mContext;
    }

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            if (myIncent.isAdReadyToDisplay()) {
                // An ad is ready.  Display the ad with one of the available show methods.
                myIncent.show(freContext.getActivity());
            } else {
                // No rewarded ad is currently available.
                myIncent.preload(null);
            }
        } catch (Exception e) {
            Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());
            return null;
        }
        return null;
    }
}
