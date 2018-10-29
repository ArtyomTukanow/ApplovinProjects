package com.applovinextension.java.functions;

import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.applovinextension.java.Activity.TestActivity;
import com.applovinextension.java.ApplovinExtension;

    public class ShowTestActivityFunction implements FREFunction {


        @Override
        public FREObject call(FREContext freContext, FREObject[] freObjects) {
            try {
                Intent intent = new Intent(freContext.getActivity().getApplicationContext(),TestActivity.class);
                freContext.getActivity().startActivity(intent);

            } catch (Exception e) {
                Log.e(ApplovinExtension.LOG_TYPE, e.getMessage());
            }

            return null;
        }
    }
