package icu.fordring.timedTask.service;

import icu.fordring.timedTask.Task.TimedTask;

/**
 * @Description 时间轮
 * @ClassName TimeWheel
 * @Author fordring
 * @date 2020.04.05 15:16
 */
public interface TimeWheel {
    /**
     * @Author fordring
     * @Description  添加一个Task
     * @Date 2020/4/5 20:37
     * @Param []
     * @return void
     **/
    void addTask(TimedTask timedTask);
    /**
     * @Author fordring
     * @Description  将时间轮转动一刻
     * @Date 2020/4/5 15:27
     * @Param []
     * @return void
     **/
    void tick();
    /**
     * @Author fordring
     * @Description  在tick之前触发，用于做一些tick前的准备工作，触发时间可能会提前几毫秒，请将时间不敏感的事务尽量放在这里
     * @Date 2020/4/5 16:11
     * @Param []
     * @return void
     **/
    void beforeTick();
    /**
     * @Author fordring
     * @Description  在tick完成后触发
     * @Date 2020/4/5 16:11
     * @Param []
     * @return void
     **/
    void afterTick();
    /**
     * @Author fordring
     * @Description  获取该时间轮的一刻所占时间(ms)
     * @Date 2020/4/5 19:21
     * @Param []
     * @return int
     **/
    int getTimeUnit();
}
