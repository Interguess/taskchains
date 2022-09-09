package de.igweb.igtaskchains.chain;

import de.igweb.igtaskchains.chain.task.ChainTask;
import lombok.Getter;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
@SuppressWarnings("unused")
public class Chain {

    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    10,
                    10,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());

    private final String id;

    private final Map<String, ChainTask> taskMap;

    private final List<ChainTask> tasks;

    public Chain(String id) {
        this.id = id;
        this.taskMap = new HashMap<>();
        this.tasks = new ArrayList<>();
    }

    public void work(boolean async) {
        if (async) {
            THREAD_POOL_EXECUTOR.execute(this::work);
        } else {
            work();
        }
    }

    public void work() {
        List<ChainTask> tasksCopy = new ArrayList<>(this.tasks);
        tasksCopy.sort(Comparator.comparingInt(task -> task.getPriority().getValue()));
        tasksCopy.forEach(ChainTask::start);
        tasks.clear();
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
