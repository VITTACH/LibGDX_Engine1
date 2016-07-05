package com.vittach.core_engine;

//для записи координат
public class MyPoint {
    private int widt, heig;

    public int x() {
        return widt;
    }

    public int y() {
        return heig;
    }

    public MyPoint(double x_posit,
                   double y_posit) {
        widt = (int) x_posit;
        heig = (int) y_posit;
    }

    public MyPoint(int xp, int yp) {
        widt = xp;
        heig = yp;
    }
}
