package com.epam.rd.autotasks;

import java.util.ArrayList;


public class CarouselRun {
    final ArrayList<Integer> carousel;
    int currentIndex;

    public CarouselRun(ArrayList<Integer> carousel) {
        this.carousel = carousel;
        this.currentIndex = 0;
    }

    public int next() {
        if (isFinished()) {
            return -1;
        }

        int value = carousel.get(currentIndex);
        carousel.set(currentIndex, value - 1);
        
        do {
            currentIndex = (currentIndex + 1) % carousel.size();
        } while (carousel.get(currentIndex) == 0 && !isFinished());

        return value;
    }

    public boolean isFinished() {
        for (int element : carousel) {
            if (element > 0) {
                return false;
            }
        }
        return true;
    }
}