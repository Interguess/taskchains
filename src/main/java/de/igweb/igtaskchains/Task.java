package de.igweb.igtaskchains;

public class Task {

    private final Chain chain;

    private final String id;

    private final int priority;

    private final Runnable runnable;

    public Task(Chain chain, String id, int priority, Runnable runnable) {
        this.chain = chain;
        this.id = id;
        this.priority = priority;
        this.runnable = runnable;
    }

    /**
     * @return the chain of the task
     */
    public Chain getChain() {
        return chain;
    }

    /**
     * @return the id of the task
     */
    public String getId() {
        return id;
    }

    /**
     * @return the priority of the task
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return the runnable of the task
     */
    public Runnable getRunnable() {
        return runnable;
    }

    /**
     * Runs the task
     */
    public void run() {
        runnable.run();
    }

}
