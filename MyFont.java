package com.vittach.core_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

@Deprecated
public class MyFont {
    private int fsize;//размер шрифта
    private BitmapFont font;//объект шрифта
    private FreeTypeFontGenerator fontload;

    //загрузка шрифта с внешнего ttf файла
    public void load(String icurrentpath) {
        fontload= new FreeTypeFontGenerator
        (Gdx.files.internal(icurrentpath));
    }

    public void setPixelSizes(int size) {
    font=fontload.generateFont(fsize=size);
    }

    //конструктор копирования
    public MyFont equal(MyFont f){
        fsize = f.fsize;
        font = f.font;
        return this;
    }

    public BitmapFont get_font() {
        return font;
    }

    public void dispose() {
        fontload.dispose();
    }

    public int get_size() {
        return fsize;
    }
}