package icu.fordring.timedTask.Task.impl;

import icu.fordring.timedTask.Task.TimedTask;
import icu.fordring.timedTask.TaskFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 循环执行的任务
 * @ClassName IntervalTimedTask
 * @Author fordring
 * @date 2020.04.05 17:48
 */
public class IntervalTimedTask implements TimedTask {
    private List<TaskFunction> callBackFunctionList;
    private int interval;
    private int round;
    public IntervalTimedTask(int interval,int round){
        this.interval=interval;
        this.round = round;
        this.callBackFunctionList = new ArrayList<>();
    }

    @Override
    public List<TaskFunction> callBackFunctions() {
        return callBackFunctionList;
    }

    @Override
    public int nextInterval() {
        return interval;
    }

    @Override
    public boolean hasNext() {
        return round>0;
    }

    @Override
    public void active() {
        for(TaskFunction taskFunction:callBackFunctionList){
            taskFunction.activeFunction(this);
        }
    }

    @Override
    public void toNext() {
        round--;
    }

}
