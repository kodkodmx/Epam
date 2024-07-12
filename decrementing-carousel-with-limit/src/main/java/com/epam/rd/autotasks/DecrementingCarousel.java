package com.epam.rd.autotasks;

import java.util.ArrayList;

public class DecrementingCarousel {
    
    protected boolean running = false;
    private final int capacity;
    protected ArrayList<Integer> carousel;

    public DecrementingCarousel(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        carousel = new ArrayList<>(capacity);
        //System.out.println("Carousel created with capacity: " + capacity);



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
