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

    public Task(Chain chain, String id, Runnable runnable) {
        this(chain, id, 0, runnable);
    }

    public Task(Chain chain, Runnable runnable) {
        this(chain, null, 0, runnable);
    }

    public Task(Chain chain, int priority, Runnable runnable) {
        this(chain, null, priority, runnable);
    }

    public Task(Chain chain, String id, int priority) {
        this(chain, id, priority, null);
    }

    public Task(Chain chain, String id) {
        this(chain, id, 0, null);
    }

    public Task(Chain chain, int priority) {
        this(chain, null, priority, null);
    }

    public Task(Chain chain) {
        this(chain, null, 0, null);
    }

    public Task(String id, int priority, Runnable runnable) {
        this(null, id, priority, runnable);
    }

    public Task(String id, Runnable runnable) {
        this(null, id, 0, runnable);
    }

    public Task(int priority, Runnable runnable) {
        this(null, null, priority, runnable);
    }

    public Task(Runnable runnable) {
        this(null, null, 0, runnable);
    }

    public Task(String id, int priority) {
        this(null, id, priority, null);
    }

    public Task(String id) {
        this(null, id, 0, null);
    }

    public Task(int priority) {
        this(null, null, priority, null);
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
