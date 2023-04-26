import de.igweb.igtaskchains.Chain;
import de.igweb.igtaskchains.Priority;

public class TestClass {

    public static void main(String[] args) {
        Chain chain = new Chain().automatic(1000);

        chain.addTask(Priority.HIGH, () -> System.out.println("This is a high priority task (#2)"));
        chain.addTask(Priority.LOW, () -> System.out.println("This is a low priority task (#4)"));
        chain.addTask(Priority.NORMAL, () -> System.out.println("This is a normal priority task (#3)"));
        chain.addTask(Priority.HIGHEST, () -> System.out.println("This is a highest priority task (#1)"));
        chain.addTask(Priority.LOWEST, () -> System.out.println("This is a lowest priority task (#5)"));

        // You can also add a task with a custom id (useful for remaining tasks)
        chain.addTask("custom-id", Priority.NORMAL, () -> System.out.println("This is a task with a custom id (#6)"));
    }
}
