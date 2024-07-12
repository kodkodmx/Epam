package com.epam.rd.autotasks;

import java.util.ArrayList;

public class DecrementingCarousel {
    
    private boolean running = false;
    //boolean full = false;
    private final int capacity;
    private final ArrayList<Integer> carousel;

    public DecrementingCarousel(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        carousel = new ArrayList<>(capacity);
    }

    public boolean addElement(int element){
        if (element <= 0 || carousel.size() == capacity || running) {
            return false;
        }
        carousel.add(element);
        return true;        
    }         

    public CarouselRun run(){
        if (running) {
            return null;
        }else{
            running = true;
            return new CarouselRun(new ArrayList<>(carousel));
        }
    }

}
