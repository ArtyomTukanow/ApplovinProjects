package {

import com.applovinextension.AS.ApplovinExtension;

import flash.desktop.NativeApplication;
import flash.display.Sprite;
import flash.display.StageAlign;
import flash.display.StageScaleMode;
import flash.events.Event;
import flash.events.MouseEvent;
import flash.ui.Multitouch;
import flash.ui.MultitouchInputMode;

public class Main extends Sprite {
    public function Main() {
        stage.scaleMode = StageScaleMode.NO_SCALE;
        stage.align = StageAlign.TOP_LEFT;
        stage.addEventListener(Event.DEACTIVATE, deactivate);

        Multitouch.inputMode = MultitouchInputMode.TOUCH_POINT;

        ApplovinExtension.init();
        ApplovinExtension.initializeSdk();

        var button1:AppButton = new AppButton("Start test activity");
        addChild(button1);
        button1.x = (stage.stageWidth - button1.width) / 2;
        button1.y = (stage.stageHeight - button1.height) / 4;
        button1.addEventListener(MouseEvent.CLICK, onButtonClick1);

        var button2:AppButton = new AppButton("Show AD");
        addChild(button2);
        button2.x = (stage.stageWidth - button2.width) / 2;
        button2.y = (stage.stageHeight - button2.height) / 1.5;
        button2.addEventListener(MouseEvent.CLICK, onButtonClick2);
    }

    private function onButtonClick1(e:MouseEvent):void {
        trace(ApplovinExtension.showTestActivity());
    }

    private function onButtonClick2(e:MouseEvent):void {
        trace(ApplovinExtension.nextAdd());
    }

    private function deactivate(e:Event):void {
        NativeApplication.nativeApplication.exit();
    }
}
}