import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
    }

    @Test
    void testAddTask() {
        taskManager.addTask("1", "Complete homework", 3);
        assertEquals(1, taskManager.getTaskCount(), "Task count should be 1 after adding a task");
    }

    @Test
    void testRemoveTask() {
        taskManager.addTask("1", "Complete homework", 3);
        boolean removed = taskManager.removeTask("1");
        assertTrue(removed, "Task should be successfully removed");
        assertEquals(0, taskManager.getTaskCount(), "Task count should be 0 after removal");
    }

    @Test
    void testGetTasksSortedByPriority() {
        taskManager.addTask("1", "Low priority task", 5);
        taskManager.addTask("2", "High priority task", 1);
        taskManager.addTask("3", "Medium priority task", 3);

        List<TaskManager.Task> sortedTasks = taskManager.getTasksSortedByPriority();
        assertEquals(1, sortedTasks.get(0).getPriority(), "First task should have the highest priority");
        assertEquals(5, sortedTasks.get(2).getPriority(), "Last task should have the lowest priority");
    }

    @Test
    void testSearchTasks() {
        taskManager.addTask("1", "Complete homework", 3);
        taskManager.addTask("2", "Prepare presentation", 2);
        taskManager.addTask("3", "Submit report", 1);

        List<TaskManager.Task> results = taskManager.searchTasks("prepare");
        assertEquals(1, results.size(), "Search should return one task matching the keyword");
        assertEquals("Prepare presentation", results.getFirst().getDescription(), "Task description should match the search keyword");
    }

    @Test
    void testAddDuplicateTaskId() {
        taskManager.addTask("1", "Complete homework", 3);
        taskManager.addTask("1", "Duplicate ID task", 4);

        assertEquals(1, taskManager.getTaskCount(), "Duplicate task ID should not be allowed");
        assertEquals("Duplicate ID task", taskManager.searchTasks("duplicate").getFirst().getDescription(), "Last added task should overwrite the previous task with the same ID");
    }

    @Test
    void testEdgeCases() {
        // Removing non-existent task
        boolean removed = taskManager.removeTask("999");
        assertFalse(removed, "Removing a non-existent task should return false");

        // Searching with no match
        List<TaskManager.Task> results = taskManager.searchTasks("nonexistent");
        assertTrue(results.isEmpty(), "Search with no matches should return an empty list");
    }
}
