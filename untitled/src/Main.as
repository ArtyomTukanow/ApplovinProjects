package {

import flash.display.Sprite;
import flash.text.TextField;

public class Main extends Sprite {
    public function Main() {
        trace(Test1(1,-2,0,-2));
        trace(Test2(1,-2,0,-2));
        trace(Test3(1,-2,0,-2));
    }


    public static function Test1(x1:int, y1:int, x2:int, y2:int):String {
        if (x1 == x2) return horizontal(y1, y2);
        else if (y1 == y2) return vertical(x1, x2);
        else return diagonale(x2 - x1, y2 - y1);
    }

    private static function horizontal(y1:int, y2:int):String {
        if (y2 > y1) return "N";//север
        else return "S";//юг
    }

    private static function vertical(x1:int, x2:int):String {
        if (x2 > x1) return "E";//восток
        else return "W";//запад
    }

    private static function diagonale(x:int, y:int):String {
        if (x > 0) {
            if (y > 0) return "NE"; //северо-восток
            else return "SE";//юго-восток
        }
        else {
            if (y > 0) return "NW"; //северо-запад
            else return "SW"; //юго-запад
        }
    }


    private static const RESULT:Object = {
        "-1":{"-1": "SW", "0": "W", "1": "NW"},
        "0":{"-1": "S", "0": "В центре", "1": "N"},
        "1":{"-1": "SE", "0": "E", "1": "NE"}};

    public static function Test2(x1:int, y1:int, x2:int, y2:int):String {
        var vX:String = "0", vY:String = "0";
        if(x2-x1 > 0) vX = "1";
        else if(x2-x1 < 0) vX = "-1";
        else vX = "0";
        if(y2-y1 > 0) vY = "1";
        else if(y2-y1 < 0) vY = "-1";
        else vY = "0";
        return RESULT[vX][vY];
    }

    public static function Test3(x1:int, y1:int, x2:int, y2:int):String {
        var x:int = x2 - x1;
        var y:int = y2 - y1;
        if (x == 0) return RESULT[0][Math.abs(y)/(y)];
        else if (y == 0) return RESULT[Math.abs(x)/(x)][0];
        return RESULT[Math.abs(x)/(x)][Math.abs(y)/(y)];
    }
}
}
