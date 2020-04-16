package icu.fordring.timedTask;

import icu.fordring.timedTask.Task.TimedTask;
import icu.fordring.timedTask.Task.TimedTaskHandle;

/**
 * @Description
 * @ClassName TaskFunction
 * @Author fordring
 * @date 2020.04.05 15:04
 */
@FunctionalInterface
public interface TaskFunction {
    void activeFunction(TimedTaskHandle timedTaskHandle);
}
