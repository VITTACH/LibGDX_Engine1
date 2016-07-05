package com.vittach.core_engine;
import com.badlogic.gdx.graphics.Color;

public class MyColor {
    private Color clr;

    //задание цвета объекта
    public MyColor
    (float x, float y, float z, float a) {
        clr = new Color(x, y, z, a);
    }

    public Color color() {
        return clr;
    }
}
