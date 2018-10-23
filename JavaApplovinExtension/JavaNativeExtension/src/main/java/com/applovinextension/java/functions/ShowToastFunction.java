package com.applovinextension.java.functions;

import android.content.Context;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class ShowToastFunction implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            String message = freObjects[0].getAsString();
            Context toastContext = freContext.getActivity();
            Toast toast = Toast.makeText(toastContext, message, Toast.LENGTH_SHORT);
            toast.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}