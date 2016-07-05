package com.vittach.core_engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Mp3Me {
    private Music sound;

    public void stop() {
        sound.stop();
    }

    public void play() {
        sound.play();
    }

    public void load(String path) {
        sound = Gdx.audio.newMusic(Gdx.files.internal(path));
    }

    public void setVolume(float numb) {
        sound.setVolume(numb);
    }

    public void setLoop(boolean flag) {
        sound.setLooping(flag);
    }

    //конструктор копирования
    public Mp3Me equals(Mp3Me sounds) {
        sound = sounds.sound;
        return this;
    }

    public boolean getStatus() {
        return sound.isPlaying();
    }

    public float getVolume() {
        return sound.getVolume();
    }

    public void pause() {
        sound.pause();
    }
}