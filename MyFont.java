package com.vittach.core_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

@Deprecated
public class MyFont {
    private int fsize;//������ ������
    private BitmapFont font;//������ ������
    private FreeTypeFontGenerator fontload;

    //�������� ������ � �������� ttf �����
    public void load(String icurrentpath) {
        fontload= new FreeTypeFontGenerator
        (Gdx.files.internal(icurrentpath));
    }

    public void setPixelSizes(int size) {
    font=fontload.generateFont(fsize=size);
    }

    //����������� �����������
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