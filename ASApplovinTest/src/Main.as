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

        var button:AppButton = new AppButton("Нажми для теста");
        addChild(button);
        button.x = (stage.stageWidth - button.width) / 2;
        button.y = (stage.stageHeight - button.height) / 2;
        button.addEventListener(MouseEvent.CLICK, onButtonClick);
    }

    private function onButtonClick(e:MouseEvent):void {
        trace(ApplovinExtension.nextAdd());
    }

    private function deactivate(e:Event):void {
        NativeApplication.nativeApplication.exit();
    }
}
}
