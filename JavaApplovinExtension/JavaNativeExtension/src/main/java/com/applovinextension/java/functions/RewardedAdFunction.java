package com.applovinextension.java.functions;

import android.content.Context;
import android.util.Log;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovinextension.java.ApplovinExtension;

import java.util.Map;

public class RewardedAdFunction implements FREFunction {

    private static AppLovinIncentivizedInterstitial myIncent;

    private static final String LEVEL_REWARD_AD = "callback_level_reward_ad";
    private static final String CODE_REWARD_VERIFIED = "callback_code_reward_verified";
    private static final String CODE_REWARD_REJECTED = "callback_code_reward_rejected";
    private static final String CODE_OVER_QUOTA = "callback_code_over_quota";
    private static final String CODE_REQUEST_FAILED = "callback_code_request_failed";
    private static final String CODE_DECLINED_TO_VIEW_AD = "callback_code_declined_to_view_ad";
    private static final String CODE_PLAYBACK_BEGAN = "callback_code_playback_began";
    private static final String CODE_PLAYBACK_ENDED = "callback_code_playback_ended";
    private static final String CODE_AD_DISPLAYED = "callback_code_ad_displayed";
    private static final String CODE_AD_HIDDEN = "callback_code_ad_hidden";
    private static final String CODE_AD_CLICKED = "callback_code_ad_clicked";

    //запускает preload для загрузки reward рекламы
    public static void init(Context context) {
        myIncent = AppLovinIncentivizedInterstitial.create(context);
        mPreloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(!isPreloadRunning && !myIncent.isAdReadyToDisplay()) {
                        loopPreload();
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(ApplovinExtension.LOG_TYPE, "InterruptedException! " + e.getMessage());
                    }
                }
            }
        });
        mPreloadThread.start();
    }

    private static boolean isPreloadRunning = false;
    public static void loopPreload()  {
        isPreloadRunning = true;
        myIncent.preload(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd appLovinAd) {
                isPreloadRunning = false;
                Log.e(ApplovinExtension.LOG_TYPE, "Reward add preloaded!");
            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                isPreloadRunning = false;
                Log.e(ApplovinExtension.LOG_TYPE, "Failed to preload reward add!");
            }
        });
    }

    private static FREContext mFreContext;
    private static Thread mPreloadThread;

    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            mFreContext = freContext;
            if(myIncent.isAdReadyToDisplay()) {
                    myIncent.show(mFreContext.getActivity(), new AppLovinAdRewardListener() {

                        @Override
                        public void userRewardVerified(AppLovinAd appLovinAd, Map<String, String> map) {
                            mFreContext.dispatchStatusEventAsync(CODE_REWARD_VERIFIED, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void userOverQuota(AppLovinAd appLovinAd, Map<String, String> map) {
                            mFreContext.dispatchStatusEventAsync(CODE_OVER_QUOTA, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void userRewardRejected(AppLovinAd appLovinAd, Map<String, String> map) {
                            mFreContext.dispatchStatusEventAsync(CODE_REWARD_REJECTED, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
                            mFreContext.dispatchStatusEventAsync(CODE_REQUEST_FAILED, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                            mFreContext.dispatchStatusEventAsync(CODE_DECLINED_TO_VIEW_AD, LEVEL_REWARD_AD);
                        }
                    }, new AppLovinAdVideoPlaybackListener() {
                        @Override
                        public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                            mFreContext.dispatchStatusEventAsync(CODE_PLAYBACK_BEGAN, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void videoPlaybackEnded(AppLovinAd appLovinAd, double v, boolean b) {
                            mFreContext.dispatchStatusEventAsync(CODE_PLAYBACK_ENDED, LEVEL_REWARD_AD);
                        }
                    }, new AppLovinAdDisplayListener() {
                        @Override
                        public void adDisplayed(AppLovinAd appLovinAd) {
                            mFreContext.dispatchStatusEventAsync(CODE_AD_DISPLAYED, LEVEL_REWARD_AD);
                        }

                        @Override
                        public void adHidden(AppLovinAd appLovinAd) {
                            mFreContext.dispatchStatusEventAsync(CODE_AD_HIDDEN, LEVEL_REWARD_AD);
                        }
                    }, new AppLovinAdClickListener() {
                        @Override
                        public void adClicked(AppLovinAd appLovinAd) {
                            mFreContext.dispatchStatusEventAsync(CODE_AD_CLICKED, LEVEL_REWARD_AD);
                        }
                    });
                }
        } catch (Exception e) {
            Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());
            return null;
        }
        return null;
    }
}