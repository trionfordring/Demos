package icu.fordring.timedTask.service.impl;

import icu.fordring.timedTask.Task.TimedTask;
import icu.fordring.timedTask.service.TickTrigger;
import icu.fordring.timedTask.service.TimeWheel;

import java.util.Stack;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description 基于数组的TimeWheel实现。
 * @ClassName ArrayTimeWheel
 * @Author fordring
 * @date 2020.04.05 15:26
 */
public class ArrayTimeWheel implements TimeWheel {
    private Stack<RoundWrappedTask> teeth[];
    private int timeUnit;
    private int nowTicks = 0;
    private ThreadPoolExecutor threadPoolExecutor;
    private Stack<TimedTask> toDo;
    private TickTrigger tickTrigger;

    public ArrayTimeWheel(int teethNumber, int timeUnit, TickTrigger tickTrigger, ThreadPoolExecutor threadPoolExecutor){
        teeth = new Stack[teethNumber];
        for(int i=0;i<teeth.length;i++){
            teeth[i]=new Stack<>();
        }
        this.timeUnit = timeUnit;
        this.threadPoolExecutor = threadPoolExecutor;
        this.tickTrigger = tickTrigger;
    }

    @Override
    public void beforeTick() {
        Stack<RoundWrappedTask> temp = new Stack<>();
        toDo = new Stack<>();
        while(!teeth[nowTicks].empty()){
            RoundWrappedTask roundWrappedTask = teeth[nowTicks].pop();
            if(roundWrappedTask.hasGet()){
                TimedTask timedTask = roundWrappedTask.getTimedTask();
                toDo.push(timedTask);
                if(timedTask.hasNext()){
                    timedTask.toNext();
                    int i = (timedTask.nextInterval()+nowTicks)%teeth.length;
                    RoundWrappedTask wrap = new RoundWrappedTask(timedTask,timedTask.nextInterval()/teeth.length);
                    if(i==nowTicks&&!wrap.hasGet()){
                        temp.push(wrap);
                    }else{
                        teeth[i].push(wrap);
                    }
                }
            }else{
                roundWrappedTask.mark();
                temp.push(roundWrappedTask);
            }
        }
    }

    @Override
    public void addTask(TimedTask timedTask) {
        int i = (timedTask.nextInterval()+nowTicks)%teeth.length;
        RoundWrappedTask roundWrappedTask = new RoundWrappedTask(timedTask,timedTask.nextInterval()/teeth.length);
        teeth[i].push(roundWrappedTask);
    }

    @Override
    public void tick() {
        if(toDo!=null&&!toDo.empty()){
            threadPoolExecutor.execute(tickTrigger.newInstance(toDo));
        }
    }

    @Override
    public void afterTick() {
        nowTicks++;
        nowTicks%=teeth.length;
    }

    @Override
    public int getTimeUnit() {
        return timeUnit;
    }


}
