package icu.fordring.timedTask.Task;

import icu.fordring.timedTask.TaskFunction;

import java.util.List;

/**
 * @Description
 * @ClassName TimedTask
 * @Author fordring
 * @date 2020.04.05 14:58
 */
public interface TimedTask extends TimedTaskHandle {
    void active();
    void activeDestroy();
    void toNext();
}
