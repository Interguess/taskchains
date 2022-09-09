package de.igweb.igtaskchains.chain.task;

import de.igweb.igtaskchains.chain.Chain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuppressWarnings("all")
public class ChainTask {

    private final String id;

    private final Priority priority;

    private final boolean async;

    private final Runnable runnable;

    public void start() {
        if (async) {
            Chain.THREAD_POOL_EXECUTOR.execute(runnable::run);
        } else {
            runnable.run();
        }
    }

    public ChainTask(String id, Priority priority, Runnable runnable) {
        this(id, priority, false, runnable);
    }

    public ChainTask(Priority priority, Runnable runnable) {
        this("", priority, false, runnable);
    }

    public ChainTask(String id, boolean async, Runnable runnable) {
        this(id, Priority.NORMAL, async, runnable);
    }

    public ChainTask(boolean async, Runnable runnable) {
        this("", Priority.NORMAL, async, runnable);
    }

    public ChainTask(Priority priority, boolean async, Runnable runnable) {
        this("", priority, async, runnable);
    }

    public ChainTask(String id, Runnable runnable) {
        this(id, Priority.NORMAL, false, runnable);
    }

}
