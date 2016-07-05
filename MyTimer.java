package com.vittach.core_engine;

import java.io.Serializable;

//самописный таймер
public class MyTimer
        implements Serializable {
    public boolean visJ = false;
    long delay=0, start, curent_t;

    //текущее время в милисекундах
    public void start(long dela) {
        delay = dela;
        start = System.currentTimeMillis();
    }

    //проверка таймера на работу
    public boolean isActive() {
        if (delay > 0)
            if ((curent_t = System.currentTimeMillis() - start) < delay)
                return true;
            else {
                visJ = false;
                stop();
            }
        return false;
    }

    public void set_Start(long start) {
        this.start = start;
    }

    public float getCurTime() {
        return (delay == 0) ? 0
             :curent_t / 1000f;
    }

    public long getMe_Start() {
        return start;
    }

    public void stop() {
        delay = 0;
    }
}
