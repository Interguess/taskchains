package de.igweb.igtaskchains;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class Chain {

    private final ScheduledThreadPoolExecutor executor;

    private final List<Task> tasks;

    /**
     * Chain's are used to execute tasks in a specific order
     */
    public Chain() {
        executor = new ScheduledThreadPoolExecutor(1);
        tasks = new ArrayList<>();
    }

    /**
     * Chain's are used to execute tasks in a specific order
     *
     * @param threads the number of threads to use
     */
    public Chain(int threads) {
        executor = new ScheduledThreadPoolExecutor(threads);
        tasks = new ArrayList<>();
    }

    /**
     * Starts an automatic worker that executes the tasks in the chain
     *
     * @param period the period in milliseconds
     * @param async  if the worker should be executed async
     */
    public Chain automatic(int period, boolean async) {
        executor.scheduleAtFixedRate(() -> execute(async), 1, period, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * Starts an automatic worker that executes the tasks in the chain synchronously
     *
     * @param period the period in milliseconds
     */
    public Chain automatic(int period) {
        return automatic(period, false);
    }

    /**
     * Executes all tasks in the chain
     *
     * @param async if the tasks should be executed asynchronously
     */
    public void execute(boolean async) {
        tasks.stream().sorted(Comparator.comparingInt(Task::getPriority).reversed()).forEach(task -> {
            if (async) {
                executor.execute(task::run);
            } else {
                task.run();
            }
        });
        tasks.clear();
    }

    /**
     * Executes all tasks in the chain synchronously
     *
     * @see #execute(boolean)
     */
    public void execute() {
        execute(false);
    }

    /**
     * @return the tasks of the chain
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the tasks of the chain
     *
     * @param tasks the tasks of the chain
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
    }

    /**
     * Clears the tasks of the chain
     */
    public void clearTasks() {
        tasks.clear();
    }

    /**
     * Adds a task to the chain
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the chain
     *
     * @param task the task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Adds a task to the chain
     *
     * @param id       the id of the task
     * @param priority the priority of the task
     * @param runnable the runnable of the task
     */
    public void addTask(String id, int priority, Runnable runnable) {
        addTask(new Task(this, id, priority, runnable));
    }

    /**
     * Adds a task to the chain
     *
     * @param id       the id of the task
     * @param runnable the runnable of the task
     */
    public void addTask(String id, Runnable runnable) {
        addTask(id, 0, runnable);
    }

    /**
     * Adds a task to the chain
     *
     * @param priority the priority of the task
     * @param runnable the runnable of the task
     */
    public void addTask(int priority, Runnable runnable) {
        addTask("::" + runnable.hashCode() * System.currentTimeMillis(), priority, runnable);
    }

    /**
     * Adds a task to the chain
     *
     * @param runnable the runnable of the task
     */
    public void addTask(Runnable runnable) {
        addTask("::" + runnable.hashCode() * System.currentTimeMillis(), 0, runnable);
    }

    /**
     * Removes a task from the chain
     *
     * @param id the id of the task to remove
     */
    public void removeTask(String id) {
        tasks.stream().filter(task -> task.getId().equals(id)).forEach(this::removeTask);
    }

}
