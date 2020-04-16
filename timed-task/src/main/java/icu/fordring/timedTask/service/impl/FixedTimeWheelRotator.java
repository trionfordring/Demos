package icu.fordring.timedTask.service.impl;

import icu.fordring.timedTask.service.TimeWheel;
import icu.fordring.timedTask.service.TimeWheelRotator;

import java.util.concurrent.locks.Lock;

/**
 * @Description 经过一定精度修正的时间轮转动器，适合于timeUnit较大的情况，但是对于时间流速的修改无法立刻响应，需要等待下一次tick出发时才会应用修改的流速。
 * @ClassName FixedTimeWheelRotator
 * @Author fordring
 * @date 2020.04.05 15:54
 */
public class FixedTimeWheelRotator implements TimeWheelRotator {
    private long lastTickTime;
    private int timeOffset=0;
    private TimeWheel timeWheel;
    private Double timeSpeed = 1.0;
    private Double newTimeSpeed = 1.0;
    private boolean status = true;
    public FixedTimeWheelRotator(TimeWheel timeWheel){
        this.timeWheel = timeWheel;
    }


    @Override
    public void run() {
        lastTickTime = System.currentTimeMillis();
        long targetTickTime = lastTickTime+(long) (timeWheel.getTimeUnit()/timeSpeed);
        while(status){
            try {
                if(timeOffset-timeWheel.getTimeUnit()<0){
                    timeOffset=timeWheel.getTimeUnit();
                }
                Thread.sleep((long) ((timeWheel.getTimeUnit()-timeOffset)/timeSpeed));
                //触发beforeTick做准备
                timeWheel.beforeTick();
                //修正时间偏移量
                timeOffset-= targetTickTime-System.currentTimeMillis();
                if(System.currentTimeMillis()<targetTickTime) {
                    //自旋，稍作等待
                    while (System.currentTimeMillis() < targetTickTime) {
                        Thread.sleep(0);
                    }
                }
                //刷新时间流速
                updateTimeSpeed();
                while(timeSpeed<=1e-5){
                    updateTimeSpeed();
                    Thread.sleep(10);
                }
                //更新下一轮的lastTickTime和targetTickTime
                lastTickTime = System.currentTimeMillis();
                targetTickTime = lastTickTime+(long) (timeWheel.getTimeUnit()/timeSpeed);
                //触发tick
                timeWheel.tick();
                //触发afterTick
                timeWheel.afterTick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTimeSpeed(){
        synchronized (newTimeSpeed){
            this.timeSpeed = newTimeSpeed;
        }
    }
    @Override
    public void setTimeSpeed(double timeSpeed) {
        synchronized (newTimeSpeed){
            this.newTimeSpeed = timeSpeed;
        }
    }

    @Override
    public double getTimeSpeed() {
        return timeSpeed;
    }
}
