package icu.fordring.timedTask.service.impl;

import icu.fordring.timedTask.Task.TimedTask;
import icu.fordring.timedTask.service.TickTrigger;

import java.util.Collection;

/**
 * @Description 用于触发一个tick
 * @ClassName SyncTickTrigger
 * @Author fordring
 * @date 2020.04.05 19:56
 */
public class SyncTickTrigger implements TickTrigger {
    private Collection<TimedTask> timedTasks;
    public SyncTickTrigger(){}
    private SyncTickTrigger(Collection<TimedTask> timedTasks){
        this.timedTasks = timedTasks;
    }

    @Override
    public TickTrigger newInstance(Collection<TimedTask> timedTasks) {
        return new SyncTickTrigger(timedTasks);
    }

    @Override
    public void run() {
        synchronized (timedTasks){
            for(TimedTask timedTask : timedTasks){
                if(timedTask.isDestroyed()){
                    timedTask.activeDestroy();
                }else{
                    timedTask.active();
                    if(!timedTask.hasNext()){
                        timedTask.activeDestroy();
                    }
                }
            }
        }
    }
}
