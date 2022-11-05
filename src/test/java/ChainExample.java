import de.igweb.igtaskchains.chain.Chain;
import de.igweb.igtaskchains.chain.task.ChainTask;
import de.igweb.igtaskchains.chain.task.Priority;

import java.util.List;

public class ChainExample {

    public static void main(String[] args) {
        Chain chain = new Chain(); // Chains are useful for data processing.

        List<ChainTask> tasks = List.of(
                new ChainTask("task-1", Priority.NORMAL, () -> System.out.println("Task 1")),
                new ChainTask("task-2", Priority.NORMAL, () -> System.out.println("Task 2")),
                new ChainTask("task-3", Priority.NORMAL, () -> System.out.println("Task 3")),
                new ChainTask("task-4", Priority.NORMAL, () -> System.out.println("Task 4"))
        );

        tasks.forEach(chain::addTask);
        chain.work();
    }

}
