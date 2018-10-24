package com.applovinextension.java.functions;

import android.content.Context;
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

public class NextAdFunction implements FREFunction {

    private static Context context;

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            context = freContext.getActivity();
            AppLovinSdk.getInstance(context).getAdService().loadNextAd( AppLovinAdSize.BANNER, new AppLovinAdLoadListener()
            {
                @Override
                public void adReceived(AppLovinAd ad)
                {
                    Toast.makeText(context, "Ad loaded! isVideoAd: " + ad.isVideoAd(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "1!", Toast.LENGTH_SHORT).show();
                    AppLovinInterstitialAdDialog interstitialAd = AppLovinInterstitialAd.create( AppLovinSdk.getInstance(context), context );
                    Toast.makeText(context, "2!", Toast.LENGTH_SHORT).show();
                    interstitialAd.showAndRender( ad );
                }

                @Override
                public void failedToReceiveAd(int errorCode)
                {
                    Toast.makeText(context, "ERROR" + errorCode, Toast.LENGTH_SHORT).show();
                }
            } );
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
