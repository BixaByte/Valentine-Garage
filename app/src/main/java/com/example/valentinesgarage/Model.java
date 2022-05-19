package com.example.valentinesgarage;

public class Model {

    private String job, description, task, assign, id, date;

    public Model(String job, String description, String task, String assign, String id, String date) {
        this.job = job;
        this.description = description;
        this.task = task;
        this.assign = assign;
        this.id = id;
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
