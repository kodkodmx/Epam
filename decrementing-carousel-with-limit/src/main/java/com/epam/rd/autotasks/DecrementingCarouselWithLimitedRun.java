package com.epam.rd.autotasks;

import java.util.ArrayList;

public class DecrementingCarouselWithLimitedRun extends DecrementingCarousel{
    private final int actionLimit;
    public DecrementingCarouselWithLimitedRun(final int capacity, final int actionLimit) {
        super(capacity);
        this.actionLimit = actionLimit;

        if (actionLimit <= 0) {
            throw new IllegalArgumentException();
        }
        carousel = new ArrayList<>(capacity);
    }

    @Override
    public CarouselRun run() {
        if (running || actionLimit <= 0) {
            return null;
        } else {
            running = true;
            return new CarouselRunWithLimitedRun(new ArrayList<>(carousel), actionLimit);
        }
    }
}

class CarouselRunWithLimitedRun extends CarouselRun {
    private int actionLimit;
    public CarouselRunWithLimitedRun(ArrayList<Integer> carousel, int actionLimit) {
        super(carousel);
        this.actionLimit = actionLimit;
    }

    @Override
    public int next() {
        if (isFinished() || actionLimit <= 0) {
            return -1;
        }

        int value = carousel.get(currentIndex);
        carousel.set(currentIndex, value - 1);
        actionLimit--;

        do {
            currentIndex = (currentIndex + 1) % carousel.size();
        } while (carousel.get(currentIndex) == 0 && !isFinished());

        return value;
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || actionLimit <= 0;
    }
}




