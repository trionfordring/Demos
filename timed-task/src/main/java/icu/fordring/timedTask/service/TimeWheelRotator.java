package icu.fordring.timedTask.service;

/**
 * @Description 负责转动时间轮
 * @ClassName TimeWheelRotator
 * @Author fordring
 * @date 2020.04.05 15:21
 */
public interface TimeWheelRotator extends Runnable {
    void setTimeSpeed(double timeSpeed);
    double getTimeSpeed();

}
