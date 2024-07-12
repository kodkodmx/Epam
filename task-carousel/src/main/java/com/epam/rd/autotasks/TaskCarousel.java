package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.List;

public class TaskCarousel {
    private final int capacity;
    private final List<Task> tasks;
    private int currentIndex;

    public TaskCarousel(int capacity) {
        this.capacity = capacity;
        this.tasks = new ArrayList<>();
        this.currentIndex = 0;
    }

    public boolean addTask(Task task) {
        if (task == null || task.isFinished() || isFull()) {
            return false;
        }
        return tasks.add(task);
    }

    public boolean execute() {
        if (isEmpty()) {
            return false;
        }

        Task currentTask = tasks.get(currentIndex);
        currentTask.execute();

        if (currentTask.isFinished()) {
            tasks.remove(currentIndex);
            if (currentIndex >= tasks.size()) {
                currentIndex = 0;
            }
        } else {
            currentIndex = (currentIndex + 1) % tasks.size();
        }

        return true;
    }

    public boolean isFull() {
        return tasks.size() >= capacity;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}