package com.epam.rd.autotasks.sprintplanning.tickets;

public class Ticket {
    private int id;
    private String name;
    private int estimate;
    private boolean completed;    

    public Ticket(int id, String name, int estimate) {
        this.id = id;
        this.name = name;
        this.estimate = estimate;
        this.completed = false;
    }

    public int getId() {        
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    public int getEstimate() {        
        return estimate;
    }
}
