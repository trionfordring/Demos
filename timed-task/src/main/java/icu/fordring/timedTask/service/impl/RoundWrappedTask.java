package icu.fordring.timedTask.service.impl;

import icu.fordring.timedTask.Task.TimedTask;

/**
 * @Description 用于包装一个TimedTask,储存圈数信息
 * @ClassName RoundWrappedTask
 * @Author fordring
 * @date 2020.04.05 20:01
 */
public class RoundWrappedTask {
    private TimedTask timedTask;
    private int needRound;

    public RoundWrappedTask(TimedTask timedTask,int needRound){
        this.needRound=needRound;
        this.timedTask=timedTask;
    }

    public TimedTask getTimedTask(){
        return timedTask;
    }

    public int getNeedRound() {
        return needRound;
    }

    public void mark(){
        needRound--;
    }

    public boolean hasGet(){
        return needRound==0;
    }
}
