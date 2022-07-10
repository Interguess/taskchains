import de.igweb.igtaskchains.chain.Chain;
import de.igweb.igtaskchains.chain.task.ChainTask;

import java.util.List;

public class ChainExample {

    public static void main(String[] args) {
        Chain chain = new Chain("test-chain"); // Chains are useful for data processing.

        List<ChainTask> tasks = List.of(
                new ChainTask("sync-task-01", false) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                },
                new ChainTask("sync-task-02", false) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                },
                new ChainTask("sync-task-03", false) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                },
                new ChainTask("async-task-01", true) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                },
                new ChainTask("async-task-02", true) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                },
                new ChainTask("async-task-03", true) {
                    @Override
                    public void run() {
                        System.out.println(getId());
                    }
                }
        );

        tasks.forEach(chain::addTask);
        chain.work();
    }

}
