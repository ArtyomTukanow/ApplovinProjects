package com.applovinextension.AS {

import flash.external.ExtensionContext;

public class ApplovinExtension {

    private static var context:ExtensionContext;

    public static function init():void {
        if(!context)
            context = ExtensionContext.createExtensionContext("com.applovinextension.xml", null);
    }

    public static function showToast(toastText:String):void {
        context.call("showToast", toastText);
    }

    public static function initializeSdk():Object {
        return context.call("initializeSdk");
    }

    public static function nextAdd():Object {
        return context.call("nextAdd");
    }
}
}
