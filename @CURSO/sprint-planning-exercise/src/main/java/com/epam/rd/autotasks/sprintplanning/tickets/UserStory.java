package com.epam.rd.autotasks.sprintplanning.tickets;

public class UserStory extends Ticket {
    UserStory[] dependsOn;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        if (dependsOn != null) {
            this.dependsOn = dependsOn;
        } else {
            this.dependsOn = new UserStory[0];
        }
    }

    @Override
    public void complete() {
        for (UserStory dependency : dependsOn) {
            if (!dependency.isCompleted()) {
                return;
            } 
                
        }
        super.complete();
        
    }

    public UserStory[] getDependencies() {
        /*UserStory[] dependsOn2 = new UserStory[this.dependsOn.length];
        System.arraycopy(this.dependsOn, 0, dependsOn2, 0, this.dependsOn.length);
        return dependsOn2;*/
        return dependsOn.clone();
    }

    @Override
    public String toString() {
        return "[US "+ getId() + "] " + getName();
    }
}
