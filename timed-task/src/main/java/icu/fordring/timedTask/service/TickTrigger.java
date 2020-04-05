package icu.fordring.timedTask.service;

import icu.fordring.timedTask.Task.TimedTask;

import java.util.Collection;

/**
 * @Description 用于触发一个kick
 * @ClassName TickTrigger
 * @Author fordring
 * @date 2020.04.05 19:48
 */
public interface TickTrigger extends Runnable{
    TickTrigger newInstance(Collection<TimedTask> timedTasks);
}
