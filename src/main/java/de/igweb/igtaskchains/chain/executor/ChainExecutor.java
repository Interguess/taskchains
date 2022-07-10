package de.igweb.igtaskchains.chain.executor;

import de.igweb.igtaskchains.chain.task.ChainTask;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ChainExecutor {

    private final ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    1,
                    1,
                    0L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>());

    public void async(ChainTask chainTask) {
        executor.execute(chainTask::run);
    }

}
