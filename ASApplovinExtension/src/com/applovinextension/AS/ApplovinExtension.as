package com.applovinextension.AS {

import flash.events.StatusEvent;
import flash.external.ExtensionContext;

public class ApplovinExtension {

    public static const LEVEL_REWARD_AD:String = "callback_level_reward_ad";
    public static const CODE_REWARD_VERIFIED:String = "callback_code_reward_verified";
    public static const CODE_REWARD_REJECTED:String = "callback_code_reward_rejected";
    public static const CODE_OVER_QUOTA:String = "callback_code_over_quota";
    public static const CODE_REQUEST_FAILED:String = "callback_code_request_failed";
    public static const CODE_DECLINED_TO_VIEW_AD:String = "callback_code_declined_to_view_ad";
    public static const CODE_PLAYBACK_BEGAN:String = "callback_code_playback_began";
    public static const CODE_PLAYBACK_ENDED:String = "callback_code_playback_ended";
    public static const CODE_AD_DISPLAYED:String = "callback_code_ad_displayed";
    public static const CODE_AD_HIDDEN:String = "callback_code_ad_hidden";
    public static const CODE_AD_CLICKED:String = "callback_code_ad_clicked";

    private static var _callbacks:Object = {};
    private static var _context:ExtensionContext;

    public static function init():void {
        if(!_context) {
            _context = ExtensionContext.createExtensionContext("com.applovinextension.ane", null);
            _context.addEventListener(StatusEvent.STATUS, onStatus);
        }
    }

    public static function showToast(toastText:String):void {
        _context.call("showToast", toastText);
    }

    public static function initializeSdk():Object {
        return _context.call("initializeSdk");
    }

    public static function nextAdd():Object {
        return _context.call("nextAdd");
    }

    public static function rewardedAd():Object {
        return _context.call("rewardedAd");
    }

    public static function showTestActivity():Object {
        return _context.call("showTestActivity");
    }

    private static function onStatus( e:StatusEvent ):void {
        trace("Statuse changed: level " + e.level + ", code " + e.code);

        if(_callbacks && _callbacks[e.level] && _callbacks[e.level][e.code]) {
            (_callbacks[e.level][e.code] as Array).every(function (f:Function, index:int, array:Array):void {
                f();
            });
        }
    }

    public static function addEventListener(level:String, code:String, f:Function):void {
        if(!_callbacks[level]) _callbacks[level] = {};
        if(!_callbacks[level][code]) _callbacks[level][code] = [];
        (_callbacks[level][code] as Array).push(f);
    }

    public static function removeEventListener(level:String, code:String, f:Function):void {
        if(!_callbacks[level] || !_callbacks[level][code]) return;
        var index:int = (_callbacks[level][code] as Array).lastIndexOf(f);
        if(index > 0) {
            (_callbacks[level][code] as Array).removeAt(index);
        }
    }
}
}
