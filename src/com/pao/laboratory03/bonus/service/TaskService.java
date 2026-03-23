package com.pao.laboratory03.bonus.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pao.laboratory03.bonus.enums.Priority;
import com.pao.laboratory03.bonus.enums.Status;
import com.pao.laboratory03.bonus.exception.TaskNotFoundException;
import com.pao.laboratory03.bonus.model.Task;

public class TaskService {
    private static TaskService instance;
    Map<String, Task> tasks = new HashMap<>();
    Map<Priority, List<Task>> tasksByPriority = new HashMap<>();
    List<String> auditLog = new ArrayList<>();

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public void addTask(String title, Priority priority) {
        Task task = new Task(title, priority, null);
        task.setId("T" + (tasks.size() + 1));
        tasks.put(task.getId(), task);
        tasksByPriority.computeIfAbsent(task.getPriority(), k -> new ArrayList<>()).add(task);
        auditLog.add("[ADD]: " + task.getId() + " - " + task.getTitle() + " (Priority: " + task.getPriority().getLevel()
                + ")");
    }

    public void assignTask(String taskId, String assignee) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.setAssignee(assignee);
            auditLog.add("[ASSIGN]: " + task.getId() + "->" + assignee);
            return;
        }
        throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
    }

    public void changeStatus(String taskId, String newStatus) {
        Task task = tasks.get(taskId);
        if (task != null) {
            Status currentStatus = task.getStatus();
            if (currentStatus.canTransitionTo(Status.valueOf(newStatus))) {
                task.setStatus(Status.valueOf(newStatus));
            } else {
                throw new RuntimeException("Invalid status transition from " + currentStatus + " to " + newStatus);
            }
            auditLog.add("[STATUS CHANGE]: " + task.getId() + " - " + currentStatus + " -> " + newStatus);
            return;
        }
        throw new TaskNotFoundException("Task with ID " + taskId + " not found.");
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return tasksByPriority.get(priority);
    }

    public Map<Status, Long> getStatusSummary() {
        Map<Status, Long> summary = new HashMap<>();
        for (Task task : tasks.values()) {
            summary.put(task.getStatus(), summary.getOrDefault(task.getStatus(), 0L) + 1);
        }
        return summary;
    }

    public List<Task> getUnassingnedTasks() {
        List<Task> unassigned = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getAssignee() == null) {
                unassigned.add(task);
            }
        }
        return unassigned;
    }

    public void printAuditLog() {
        for (String log : auditLog) {
            System.out.println(log);
        }
    }

    public double getTotalUrgencyScore(int baseDays) {
        double totalScore = 0;
        for (Task task : tasks.values()) {
            totalScore += task.getPriority().calculateScore(baseDays);
        }
        return totalScore;
    }
}
