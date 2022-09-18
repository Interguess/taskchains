package de.igweb.igtaskchains.chain;

import de.igweb.igtaskchains.chain.task.ChainTask;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Getter
@NoArgsConstructor
@SuppressWarnings("unused")
public class Chain {

    public static final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);

    private final Map<String, ChainTask> taskMap = new HashMap<>();

    private final List<ChainTask> tasks = new ArrayList<>();

    public Chain(int period) {
        this();
        scheduledThreadPoolExecutor.scheduleAtFixedRate(this::work, 0, period, TimeUnit.MILLISECONDS);
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

    public void work() {
        scheduledThreadPoolExecutor.execute(() -> {
            List<ChainTask> tasksCopy = new ArrayList<>(this.tasks);
            tasksCopy.sort(Comparator.comparingInt(task -> task.getPriority().getValue()));
            tasksCopy.forEach((task) -> task.getRunnable().run());
            tasks.clear();
        });
    }

}
