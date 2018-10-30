package {

import com.applovinextension.AS.ApplovinExtension;

import flash.display.Sprite;
import flash.display.StageAlign;
import flash.display.StageScaleMode;
import flash.events.MouseEvent;
import flash.ui.Multitouch;
import flash.ui.MultitouchInputMode;

public class ApplovinTestMain extends Sprite {

    private var _textCounter:TabloTextField;

    public function ApplovinTestMain() {
        stage.scaleMode = StageScaleMode.NO_SCALE;
        stage.align = StageAlign.TOP_LEFT;

        Multitouch.inputMode = MultitouchInputMode.TOUCH_POINT;

        ApplovinExtension.init();
        ApplovinExtension.initializeSdk();


        _textCounter = new TabloTextField();
        addChild(_textCounter);
        _textCounter.width =
                _textCounter.x = (stage.stageWidth) / 2;
        _textCounter.y = 100;

        var simpleAdBtn:AppButton = new AppButton("Show AD");
        addChild(simpleAdBtn);
        simpleAdBtn.x = (stage.stageWidth - simpleAdBtn.width) / 2;
        simpleAdBtn.y = 300;
        simpleAdBtn.addEventListener(MouseEvent.CLICK, onSimpleAdBtnClick);

        var rewardAdBtn:AppButton = new AppButton("Show Reward AD");
        addChild(rewardAdBtn);
        rewardAdBtn.x = (stage.stageWidth - rewardAdBtn.width) / 2;
        rewardAdBtn.y = 500;
        rewardAdBtn.addEventListener(MouseEvent.CLICK, onRewardAdBtnClick);

        ApplovinExtension.addEventListener(ApplovinExtension.LEVEL_REWARD_AD, ApplovinExtension.CODE_REWARD_VERIFIED, onRewardVerified);
    }





    /*

    EVENTS

     */

    private function onSimpleAdBtnClick(e:MouseEvent):void {
        ApplovinExtension.nextAdd();
    }


    private function onRewardAdBtnClick(e:MouseEvent):void {
        ApplovinExtension.rewardedAd();
    }

    private function onRewardVerified():void {
        _textCounter.addRewardCount();
    }
}
}