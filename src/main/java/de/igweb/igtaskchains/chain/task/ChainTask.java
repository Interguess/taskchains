package de.igweb.igtaskchains.chain.task;

import de.igweb.igtaskchains.chain.executor.ChainExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuppressWarnings("all")
public abstract class ChainTask {

    private final String id;

    private final Priority priority;

    private final boolean async;

    @Deprecated
    public abstract void run();

    public void start(ChainExecutor executor) {
        if (async) {
            executor.async(this);
        } else {
            run();
        }
    }

    public ChainTask(String id, Priority priority) {
        this(id, priority, false);
    }

    public ChainTask(Priority priority) {
        this("", priority, false);
    }

    public ChainTask(String id, boolean async) {
        this(id, Priority.NORMAL, async);
    }

    public ChainTask(boolean async) {
        this("", Priority.NORMAL, async);
    }

    public ChainTask(Priority priority, boolean async) {
        this("", priority, async);
    }

    public ChainTask(String id) {
        this(id, Priority.NORMAL, false);
    }

}
