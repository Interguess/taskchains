package de.igweb.igtaskchains.chain;

import de.igweb.igtaskchains.chain.task.ChainTask;
import de.igweb.igtaskchains.chain.task.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
@NoArgsConstructor
@SuppressWarnings("unused")
public class Chain {

    public static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(5);

    private final Map<String, ChainTask> taskMap = new HashMap<>();

    private final List<ChainTask> tasks = new ArrayList<>();

    public Chain(int period) {
        SCHEDULED_THREAD_POOL_EXECUTOR.scheduleAtFixedRate(this::work, period, period, TimeUnit.MILLISECONDS);
    }

    public ChainTask getTask(String id) {
        return taskMap.get(id);
    }

    public void addTask(ChainTask task) {
        taskMap.put(task.getId(), task);
        tasks.add(task);
    }

    public void addTask(String id, Runnable runnable) {
        addTask(new ChainTask(id, runnable));
    }

    public void addTask(Runnable runnable) {
        addTask(new ChainTask(runnable));
    }

    public void addTask(String id, Priority priority, Runnable runnable) {
        addTask(new ChainTask(id, priority, runnable));
    }

    public void addTask(Priority priority, Runnable runnable) {
        addTask(new ChainTask(priority, runnable));
    }

    @Deprecated // use removeTask(String id) instead
    public void removeTask(ChainTask task) {
        removeTask(task.getId());
    }

    public void removeTask(String id) {
        taskMap.remove(id);
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void work() {
        SCHEDULED_THREAD_POOL_EXECUTOR.execute(() -> {
            List<ChainTask> tasksCopy = new ArrayList<>(this.tasks);
            tasksCopy.sort(Comparator.comparingInt(task -> task.getPriority().getValue()));
            tasksCopy.forEach((task) -> task.getRunnable().run());
            tasks.clear();
        });
    }

}
