package com.fresh.reversestopwatch.time;

import static java.lang.String.*;

import android.os.SystemClock;

/**
 * Класс секундомера с возможностью обратного хода времени
 */
public class Stopwatch {
    private long currentTime = 0L;
    private long pauseTime = 0L;
    private long startTime;
    private boolean reverse = false;

    /**
     *  Метод начала отсчёта
     */
    public void start() {
        startTime = SystemClock.uptimeMillis();
    }

    /**
     * Метод обновления времени
     */
    public void updateTime() {
        currentTime = pauseTime;
        if (! reverse) {
            currentTime += SystemClock.uptimeMillis() - startTime;
        }
        else {
            currentTime -= SystemClock.uptimeMillis() - startTime;
        }
    }

    /**
     * Метод, ставящий таймер на паузу
     */
    public void pause() {
        pauseTime = currentTime;
    }

    /**
     * Метод, меняющий ход времени
     */
    public void reverse() {
        reverse = ! reverse;
    }

    /**
     * Сброс значений
     */
    public void reset() {
        reverse = false;
        pauseTime = 0;
    }

    public long getTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        int millis = (int) currentTime % 1000;
        int seconds = (int) currentTime / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;

        seconds %= 60;
        minutes %= 60;

        if (hours > 0) {
            out.append(hours);
            out.append(':');
        }
        if (minutes > 0) {
            out.append(format("%02d:", minutes));
        }
        out.append(format("%02d.%03d", seconds, millis));
        return out.toString();
    }
}
