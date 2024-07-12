package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

public class Sprint {
    private final Ticket[] tickets;
    private final int ticketsLimit;
    private final int capacity;
    private int ticketsCount;
    private int totalEstimate;

    public Sprint(int capacity, int ticketsLimit) {
        this.tickets = new Ticket[ticketsLimit]; 
        this.ticketsLimit = ticketsLimit;
        this.ticketsCount = 0;
        this.capacity = capacity;
        this.totalEstimate = 0;
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory == null || userStory.isCompleted() || userStory.getEstimate() + totalEstimate > capacity || ticketsCount >= ticketsLimit) {
            return false;
        }
        for (UserStory dependency : userStory.getDependencies()) {
            boolean dependencyFound = false;
            for (Ticket ticket : tickets) {
                if (ticket == dependency) {
                    dependencyFound = true;
                    break;
                }
            }
            if (!dependencyFound) {
                return false; 
            }
        }
        tickets[ticketsCount++] = userStory;
        totalEstimate += userStory.getEstimate();
        return true;
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport == null || bugReport.isCompleted() || bugReport.getEstimate() + totalEstimate > capacity || ticketsCount >= ticketsLimit) {
            return false;
        }
        tickets[ticketsCount++] = bugReport;
        totalEstimate += bugReport.getEstimate();
        return true;
    }

    public Ticket[] getTickets() {
        Ticket[] currentTickets = new Ticket[ticketsCount];
        System.arraycopy(tickets, 0, currentTickets, 0, ticketsCount);
        return currentTickets;
    }

    public int getTotalEstimate() {
        return totalEstimate;
    }
}