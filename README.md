

# Task Manager System Documentation

## **Author Information**
- **Name**: Tai Tran Viet
- **Student ID**: BIT220137
- **Email**: tvtai@cmcu.edu.vn

---

## Overview

The **TaskManager** project showcases a Java-based task management system. It provides functionality for creating, managing, and searching tasks, along with unit tests written using **JUnit 5** to ensure reliable functionality.

---

## Features

The `TaskManager` class provides the following functionality:
1. **Task Management**:
    - Adds, removes, and retrieves tasks.
2. **Task Prioritization**:
    - Orders tasks by priority (1 for high, 5 for low).
3. **Search**:
    - Finds tasks by keywords in their descriptions.
4. **Task Count**:
    - Retrieves the current count of tasks.

---

## Implementation Details

### **TaskManager Class**

The `TaskManager` class is designed to manage tasks with the following key elements:

#### **Task Class**
1. **Attributes**:
    - `id`: A unique identifier for each task.
    - `description`: A string describing the task.
    - `priority`: An integer (1 to 5) representing the task priority.

2. **Methods**:
    - Getters for `id`, `description`, and `priority`.
    - Overrides `toString` for a human-readable representation.

#### **TaskManager Class**
1. **Attributes**:
    - `taskMap`: A `Map` for storing tasks by their ID.
    - `taskQueue`: A `PriorityQueue` for sorting tasks by priority.

2. **Methods**:
    - `addTask(String id, String description, int priority)`: Adds a task. Overwrites existing tasks with the same ID.
    - `removeTask(String id)`: Removes a task by its ID.
    - `getTasksSortedByPriority()`: Returns all tasks sorted by priority.
    - `searchTasks(String keyword)`: Searches for tasks by a keyword in the description.
    - `getTaskCount()`: Returns the total number of tasks.

**Code Sample:**
```java
import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final Map<String, Task> taskMap = new HashMap<>();
    private final PriorityQueue<Task> taskQueue = new PriorityQueue<>(Comparator.comparing(Task::getPriority));

    public static class Task {
        private final String id;
        private final String description;
        private final int priority;

        public Task(String id, String description, int priority) {
            this.id = id;
            this.description = description;
            this.priority = priority;
        }

        public String getId() {
            return id;
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
}
```

---

## Testing Details

Unit tests ensure the reliability of the `TaskManager` class, covering key features, edge cases, and exception handling.

### **Test Cases**

1. **Task Addition**:
    - Confirms tasks are added correctly.
    - Validates that duplicate task IDs overwrite existing tasks.

2. **Task Removal**:
    - Confirms tasks are removed by ID.
    - Ensures removal fails gracefully for non-existent IDs.

3. **Task Prioritization**:
    - Validates correct sorting of tasks by priority.

4. **Task Search**:
    - Ensures keyword-based search returns the correct results.

5. **Edge Cases**:
    - Tests for handling empty searches, non-existent IDs, and duplicate additions.

**Test Code Sample:**
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager taskManager = new TaskManager();

    @Test
    void testAddTask() {
        taskManager.addTask("1", "Complete homework", 3);
        assertEquals(1, taskManager.getTaskCount());
    }

    @Test
    void testRemoveTask() {
        taskManager.addTask("1", "Complete homework", 3);
        assertTrue(taskManager.removeTask("1"));
    }

    @Test
    void testGetTasksSortedByPriority() {
        taskManager.addTask("1", "Low priority", 5);
        taskManager.addTask("2", "High priority", 1);
        assertEquals(1, taskManager.getTasksSortedByPriority().getFirst().getPriority());
    }

    @Test
    void testSearchTasks() {
        taskManager.addTask("1", "Homework", 3);
        assertEquals(1, taskManager.searchTasks("Homework").size());
    }
}
```

---

## How to Run the Tests

1. **Prerequisites**:
    - Install JDK 8+ and Maven/Gradle.
    - Include **JUnit 5** in the dependencies.

2. **Steps**:
    - Clone the repository.
    - Navigate to the project directory.
    - Run tests:
        - **Maven**: `mvn test`
        - **Gradle**: `gradle test`

3. **Expected Output**:
    - All tests pass without errors, confirming functionality.

---

## Result

All tests passed, verifying the `TaskManager` class.

---

## License

This project is licensed under the MIT License.