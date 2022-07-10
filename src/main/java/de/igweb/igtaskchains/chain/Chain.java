package de.igweb.igtaskchains.chain;

import de.igweb.igtaskchains.chain.executor.ChainExecutor;
import de.igweb.igtaskchains.chain.task.ChainTask;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@SuppressWarnings("unused")
public class Chain {

    private final String id;

    private final Map<String, ChainTask> taskMap;

    private final List<ChainTask> tasks;

    private final ChainExecutor executor;

    public Chain(String id) {
        this.id = id;
        this.taskMap = new HashMap<>();
        this.tasks = new ArrayList<>();
        this.executor = new ChainExecutor();
    }

    public void work() {
        work(false);
    }

    public void work(boolean async) {
        if (async) {
            new Thread(() -> {
                List<ChainTask> tasksCopy = new ArrayList<>(this.tasks);
                tasks.clear();
                tasksCopy.forEach(task -> task.start(executor));
            }).start();
        } else {
            List<ChainTask> tasksCopy = new ArrayList<>(this.tasks);
            tasks.clear();
            tasksCopy.forEach(task -> task.start(executor));
        }
    }

    public ChainTask getTask(String id) {
        return taskMap.get(id);
    }

    public void addTask(ChainTask task) {
        taskMap.put(task.getId(), task);
        tasks.add(task);
    }

    public void removeTask(ChainTask task) {
        taskMap.values().remove(task);
        tasks.remove(task);
    }

    public void removeTask(String id) {
        removeTask(getTask(id));
    }

}
