package de.igweb.igtaskchains.chain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChainTask {

    private final String id;

    private final Priority priority;

    private final Runnable runnable;

    public ChainTask(String id, Runnable runnable) {
        this(id, Priority.NORMAL, runnable);
    }

    public ChainTask(Runnable runnable) {
        this(String.valueOf(System.currentTimeMillis()) + runnable.hashCode(), runnable);
    }

    public ChainTask(Priority priority, Runnable runnable) {
        this(String.valueOf(System.currentTimeMillis()) + runnable.hashCode(), priority, runnable);
    }

}
