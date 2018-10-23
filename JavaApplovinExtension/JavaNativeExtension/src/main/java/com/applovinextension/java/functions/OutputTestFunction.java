package com.applovinextension.java.functions;

import android.content.Context;
import android.widget.Toast;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class OutputTestFunction implements FREFunction {
    @Override
    public FREObject call(FREContext freContext, FREObject[] freObjects) {
        try {
            int number = freObjects[0].getAsInt();
            number ++;
            Context toastContext = freContext.getActivity();
            Toast toast = Toast.makeText(toastContext, "Number is: " + number, Toast.LENGTH_SHORT);
            toast.show();
            return FREObject.newObject(number);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
