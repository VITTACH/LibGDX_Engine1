package com.vittach.ijumpjack.controllers;

import com.badlogic.gdx.Gdx;
import com.vittach.core_engine.MyColor;
import com.vittach.core_engine.MyPoint;
import com.vittach.core_engine.MyTimer;
import com.vittach.interfaces.ProcessorInput;
import com.vittach.ijumpjack.game_engine.Engine;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Control
    implements ProcessorInput {
    protected boolean stop;
    public MyTimer joy_timer;
    double angle, numerat, denomin;
    float koefW, koefH;int we, he, ew;
    MyPoint[] startP = new MyPoint[2];
    private int xpos, ypos, Ofset = 0;
    private int ID = -1, eh, ijoy_pos;

    public Control(int joys_XY) {
        stop = true;
        ijoy_pos = joys_XY;
        joy_timer= new MyTimer();
    }

    public boolean joy_states() {
        if (stop) return false;
        else return stop = true;
    }

    public boolean get_joyvis() {
        joy_timer.isActive();
        return joy_timer.visJ;
    }

    MyPoint get_pos(int number) {
        return startP[number];
    }

    @Override
    public void setIDOffset(int set) {
        Ofset = set;
    }

    @Override
    public boolean
    mouseMoved(int screnx,int screny){
        return true;
    }

    @Override
    public boolean scrolled(int mnt) {
        return true;
    }

    @Override
    public boolean keyTyped(char ch) {
        return true;
    }

    @Override
    public boolean keyDown(int ikey) {
        return true;
    }

    @Override
    public boolean keyUp(int id_key) {
        return true;
    }

    @Override
    public boolean
        touchUp(int x, int y, int p, int btn) {
        Engine.usedID.remove(p + Ofset);
        if (!joy_timer.isActive() && ID >= 0) {
            ID = -1;
            stop = false;
            joy_timer.start(2000);
            startP[1] = startP[0];
        }
        return true;
    }

    public void setJP() {
        ew = (Engine.wid - Engine.widt) / 2;
        koefW = (float) Engine.wid / (float) Engine.widt;//установка чувствительности
        eh = (Engine.heidh- Engine.hei) / 2;
        koefH = (float) Engine.heidh/ (float) Engine.hei;//установка чувствительности
    }

    //печать грибковых джойстика
    public void joyPrint(MyColor clr) {
        if (get_joyvis()) {
            int Sradius, Lradius;
            Gdx.gl.glEnable(3042);
            Gdx.gl.glBlendFunc(770, 771);
            ShapeRenderer shape = new ShapeRenderer();
            Sradius = (Lradius = Engine.widt / 20) / 2;
            shape.begin(ShapeRenderer.ShapeType.Line);
            shape.setColor(clr.color ());

            shape.circle(get_pos(0).x() + we, Engine.hei - get_pos(0).y() + he, Lradius);
            shape.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.circle(get_pos(1).x() + we, Engine.hei - get_pos(1).y() + he, Sradius);
            shape.end();
            shape.dispose();//itrash
        }
    }


    //сдвиг по X
    public float analogX() {
        int hgh = Engine.hei;
        return (!joy_timer.isActive() && get_joyvis()) ? (xpos - startP[0].x()) / (hgh / 256f) : 0;
    }

    //сдвиг по Y
    public float analogY() {
        int hgh = Engine.hei;
        return (!joy_timer.isActive() && get_joyvis()) ? (ypos - startP[0].y()) / (hgh / 256f) : 0;
    }

    @Override
    public boolean
        touchDown(int x, int y, int numb, int b) {
        xpos = x -= ew;
        ypos = y -= eh;
        if (!Engine.usedID.contains(numb + Ofset))
            if ((x > 0&& x < Engine.widt / 4&& y > Engine.hei - Engine.hei / 4&& y < Engine.hei&& ijoy_pos == 0) ||
               (ijoy_pos==1&&x>Engine.widt-Engine.widt/4&&x<Engine.widt&&y>Engine.hei-Engine.hei/4&&y<Engine.hei)){
                //сделай видимым джойстик(do visible joy)
                if (!joy_timer.visJ) {
                    joy_timer.visJ = true;
                    ID = numb;
                    we = (int) ((x - x / koefW) * koefW);
                    //координаты для джойстика
                    startP[0] = new MyPoint(x, y);
                    startP[1] = new MyPoint(x, y);
                    y = Engine.hei - y;
                    he = (int) ((y - y / koefH) * koefH);
                } else if (xpos >= startP[0].x() - Engine.widt / 20 && xpos <= startP[0].x() + Engine.widt / 20 &&
                           ypos >= startP[0].y() - Engine.widt / 20 && ypos <= startP[0].y() + Engine.widt / 20) {
                    startP[1] = new MyPoint(x, ypos);
                    joy_timer.stop();
                    ID = numb;
                }
                Engine.usedID.add(numb + Ofset);
            }
        return true;
    }

    @Override
    public boolean
        touchDragged(int x, int y, int numb) {
        xpos = x -= ew;
        ypos = y -= eh;
        if ((x > 0 && x < Engine.widt / 4 && y > Engine.hei - Engine.hei / 4 && y < Engine.hei && ijoy_pos == 0) ||
           (ijoy_pos==1&&x>Engine.widt-Engine.widt/4&&x<Engine.widt&&y>Engine.hei-Engine.hei/4f && y < Engine.hei))
            if (Engine.usedID.contains(numb + Ofset)) {
                //сделай видимым джойстик(do visible joy)
                if (!joy_timer.visJ) {
                    joy_timer.visJ = true;
                    ID = numb;
                    we = (int) ((x - x / koefW) * koefW);
                    //координаты для джойстика
                    startP[0] = new MyPoint(x, y);
                    startP[1] = new MyPoint(x, y);
                    y = Engine.hei - y;
                    he = (int) ((y - y / koefH) * koefH);
                } else if (x >= startP[0].x() - Engine.widt / 20 && x <= startP[0].x() + Engine.widt / 20 &&
                           y >= startP[0].y() - Engine.widt / 20 && y <= startP[0].y() + Engine.widt / 20 && ID< 0)
                    ID = numb;//запишу ID пальца
            }
        if (numb == ID) {
            if (joy_timer.isActive())
                joy_timer.stop();
            //если палец на джойстике
            if ((xpos - startP[0].x()) * (xpos - startP[0].x()) + (ypos - startP[0].y()) * (ypos - startP[0].y()) <
                Math.pow(Engine.widt / 20, 2))
                startP[1] = new MyPoint(x, y);
            else {
                numerat = (x - startP[0].x());
                denomin = Math.sqrt(
                (xpos - startP[0].x()) * (xpos - startP[0].x()) +
                (ypos - startP[0].y()) * (ypos - startP[0].y()));
                angle = Math.acos(numerat / denomin);
                if (y < startP[0].y()) angle = -angle;
                startP[1] = new MyPoint
                (startP[0].x()+Engine.widt/20*Math.cos(angle),
                startP[0].y()+Engine.widt/20*Math.sin(angle));
            }
        }
        return true;
    }
}