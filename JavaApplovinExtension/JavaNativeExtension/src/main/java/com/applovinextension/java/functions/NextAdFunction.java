package com.applovinextension.java.functions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;
import com.applovinextension.java.ApplovinExtension;

public class NextAdFunction implements FREFunction {

    private static Context mContext;
    private static AppLovinInterstitialAdDialog interstitialAd;

    private static void setContext(Context context) {
        if(context == mContext)
            return;

        mContext = context;
        interstitialAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(mContext), mContext);
    }

    private static Context getContext() {
        return mContext;
    }

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            setContext(freContext.getActivity().getApplicationContext());
            AppLovinSdk.getInstance(getContext()).getAdService().loadNextAd( AppLovinAdSize.INTERSTITIAL, new AppLovinAdLoadListener()
            {
                @Override
                public void adReceived(AppLovinAd ad) {
                    Log.e(ApplovinExtension.LOG_TYPE, "Ad loaded!");

                    interstitialAd.showAndRender(ad);
                    Log.e(ApplovinExtension.LOG_TYPE, "Ad showed And Rendered!");

                }

                @Override
                public void failedToReceiveAd(int errorCode)
                {
                    Log.e(ApplovinExtension.LOG_TYPE, "failedToReceiveAd! errorCode: " + errorCode);

                    Toast.makeText(getContext(), "ERROR" + errorCode, Toast.LENGTH_SHORT).show();
                }
            } );
        } catch (Exception e) {
            try {
                Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());

                return FREObject.newObject(e.getMessage());
            } catch (Exception e1) {
                return null;
            }
        }
        return null;
    }
}
