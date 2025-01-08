import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final Map<String, Task> taskMap = new HashMap<>();
    private final PriorityQueue<Task> taskQueue = new PriorityQueue<>(Comparator.comparing(Task::getPriority));

    // Task Class
    public static class Task {
        private final String id;
        private final String description;
        private final int priority; // 1 (High) to 5 (Low)

        public Task(String id, String description, int priority) {
            this.id = id;
            this.description = description;
            this.priority = priority;
        }

        public String getDescription() {
            return description;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Task{id='" + id + "', description='" + description + "', priority=" + priority + '}';
        }
    }

    // Add a task
    public void addTask(String id, String description, int priority) {
        Task task = new Task(id, description, priority);
        taskMap.put(id, task);
        taskQueue.add(task);
    }

    // Remove a task by ID
    public boolean removeTask(String id) {
        Task task = taskMap.remove(id);
        return task != null && taskQueue.remove(task);
    }

    // Get all tasks sorted by priority
    public List<Task> getTasksSortedByPriority() {
        return taskQueue.stream().sorted(Comparator.comparing(Task::getPriority)).collect(Collectors.toList());
    }

    // Search tasks by description
    public List<Task> searchTasks(String keyword) {
        return taskMap.values().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Get task count
    public int getTaskCount() {
        return taskMap.size();
    }
}