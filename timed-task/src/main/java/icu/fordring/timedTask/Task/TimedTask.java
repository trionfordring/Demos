package icu.fordring.timedTask.Task;

import icu.fordring.timedTask.TaskFunction;

import java.util.List;

/**
 * @Description
 * @ClassName TimedTask
 * @Author fordring
 * @date 2020.04.05 14:58
 */
public interface TimedTask {
    List<TaskFunction> callBackFunctions();
    int nextInterval();
    boolean hasNext();
    void active();
    void toNext();
}
