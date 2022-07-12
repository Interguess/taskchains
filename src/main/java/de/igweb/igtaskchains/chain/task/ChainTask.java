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

}
