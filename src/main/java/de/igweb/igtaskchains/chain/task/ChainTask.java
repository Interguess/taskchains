package de.igweb.igtaskchains.chain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public class ChainTask {

    private final String id;

    private final Priority priority;

    private final Runnable runnable;

    public ChainTask(Priority priority, Runnable runnable) {
        this(generateId(), priority, runnable);
    }

    public ChainTask(String id, Runnable runnable) {
        this(id, Priority.NORMAL, runnable);
    }

    public ChainTask(Runnable runnable) {
        this(generateId(), Priority.NORMAL, runnable);
    }

    private static String generateId() {
        return "task-" + System.nanoTime() + "@" + new Random().nextInt(1000);
    }

}
