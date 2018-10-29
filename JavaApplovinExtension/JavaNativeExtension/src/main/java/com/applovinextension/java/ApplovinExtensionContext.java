package com.applovinextension.java;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.applovinextension.java.functions.*;

import java.util.HashMap;
import java.util.Map;

public class ApplovinExtensionContext extends FREContext {

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }

    @Override
    public Map<String, FREFunction> getFunctions() {
        Map<String, FREFunction> map = new HashMap<String, FREFunction>();
        map.put("showToast", new ShowToastFunction());
        map.put("initializeSdk", new ApplovinInitFunction());
        map.put("nextAdd", new NextAdFunction());
        map.put("rewardedAd", new RewardedAdFunction());
        map.put("showTestActivity", new ShowTestActivityFunction());
        return map;
    }
}
