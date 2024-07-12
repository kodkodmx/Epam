package com.epam.rd.autotasks;

import java.util.ArrayList;

public class HalvingCarousel extends DecrementingCarousel {

    public HalvingCarousel(final int capacity) {
        super(capacity);
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        super.capacity = capacity;
        carousel = new ArrayList<>(capacity);
        //System.out.println("Carousel created with capacity: " + capacity);
    }

    @Override
    public CarouselRun run() {
        if (running) {
            return null;
        } else {
            running = true;
            return new HalvingCarouselRun(new ArrayList<>(carousel));
        }
    }

    public void halve() {
        for (int i = 0; i < carousel.size(); i++) {
            int value = carousel.get(i);
            carousel.set(i, value / 2);
        }
    }
}

    class HalvingCarouselRun extends CarouselRun {
        public HalvingCarouselRun(ArrayList<Integer> carousel) {
            super(carousel);
        }

        @Override
        public int next() {
            if (isFinished()) {
                return -1;
            }

            int value = carousel.get(currentIndex);
            carousel.set(currentIndex, value / 2);
            
            do {
                currentIndex = (currentIndex + 1) % carousel.size();
            } while (carousel.get(currentIndex) == 0 && !isFinished());

            return value;
        }
    }