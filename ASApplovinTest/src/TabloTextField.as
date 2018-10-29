package {
import flash.display.Shape;
import flash.display.Sprite;
import flash.events.Event;
import flash.text.TextField;
import flash.text.TextFieldAutoSize;
import flash.text.TextFormat;

public class TabloTextField extends Sprite {

    private static const REWARD_AD_COUNTER_TEXT:String = "Reward counter: ";
    private var _rewardCounterTextField:TextField;
    private var _rewardCounter:int = 0;

    private var shape:Shape;

    public function TabloTextField() {
        addEventListener(Event.ADDED_TO_STAGE, onAdded);
    }

    public function addRewardCount():void {
        _rewardCounter ++;
        updateRewardCounterTextField();
    }

    private function updateRewardCounterTextField():void {
        if(_rewardCounterTextField)
            _rewardCounterTextField.text = REWARD_AD_COUNTER_TEXT + "" + _rewardCounter;
    }

    private function onAdded(e:Event):void {
        removeEventListener(Event.ADDED_TO_STAGE, onAdded);
        shape = new Shape();
        addChild(shape);

        var tf:TextFormat = new TextFormat();
        tf.size = 25;

        _rewardCounterTextField = new TextField();
        _rewardCounterTextField.mouseEnabled = false;
        _rewardCounterTextField.autoSize = TextFieldAutoSize.CENTER;
        _rewardCounterTextField.defaultTextFormat = tf;
        updateRewardCounterTextField();
        _rewardCounterTextField.x = (280 - _rewardCounterTextField.width) / 2;
        _rewardCounterTextField.y = 10;
        addChild(_rewardCounterTextField);
        drawShape(0xFFFFFF);
    }

    private function drawShape(color:uint):void {
        shape.graphics.clear();
        shape.graphics.beginFill(color);
        shape.graphics.drawRect(0, -25, 280, 100);
        shape.graphics.endFill();
    }
}
}
