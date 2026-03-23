package com.pao.laboratory03.bonus.model;

import com.pao.laboratory03.bonus.enums.Priority;
import com.pao.laboratory03.bonus.enums.Status;

public class Task {
    private String id, title, assignee = null;
    private Status status;
    private Priority priority;

    public Task(String title, Priority priority, String assignee) {
        this.status = Status.TODO;
        this.priority = priority;
        this.assignee = assignee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
