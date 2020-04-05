package icu.fordring.timedTask.Task.impl;

import icu.fordring.timedTask.Task.TimedTask;
import icu.fordring.timedTask.TaskFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 定时器
 * @ClassName TimeOutTimeTask
 * @Author fordring
 * @date 2020.04.05 19:09
 */
public class TimeOutTimeTask implements TimedTask {
    private List<TaskFunction> callBackFunctionList;
    private int interval;
    public TimeOutTimeTask(int interval){
        callBackFunctionList = new ArrayList<>();
        this.interval=interval;
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
        return false;
    }

    @Override
    public void active() {
        for(TaskFunction taskFunction:callBackFunctionList){
            taskFunction.activeFunction(this);
        }
    }

    @Override
    public void toNext() { }
}
