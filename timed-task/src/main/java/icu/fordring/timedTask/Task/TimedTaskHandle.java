package icu.fordring.timedTask.Task;

import icu.fordring.timedTask.TaskFunction;

import java.util.List;

/**
 * @Description
 * @ClassName TimedTaskHandle
 * @Author fordring
 * @date 2020.04.06 12:08
 */
public interface TimedTaskHandle {
    boolean isRunning();
    boolean isDestroyed();
    void stop();
    void goOn();
    List<TaskFunction> callBackFunctions();
    List<TaskFunction> whenDestroyFunctions();
    void destroy();
    boolean hasNext();
    int nextInterval();
}
