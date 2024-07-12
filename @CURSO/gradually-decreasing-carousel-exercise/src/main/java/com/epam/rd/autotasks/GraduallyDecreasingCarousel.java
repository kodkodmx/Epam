package com.epam.rd.autotasks;

import java.util.ArrayList;

public class GraduallyDecreasingCarousel extends DecrementingCarousel {
    public GraduallyDecreasingCarousel(final int capacity) {
        super(capacity);
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public CarouselRun run() {
        if (running) {
            return null;
        } else {
            running = true;
            return new GraduallyDecreasingCarouselRun(new ArrayList<>(carousel));
        }
    }

    public class GraduallyDecreasingCarouselRun extends CarouselRun {
        private final ArrayList<Integer> decrements;

        public GraduallyDecreasingCarouselRun(ArrayList<Integer> carousel) {
            super(carousel);
            decrements = new ArrayList<>();
            for (int i = 0; i < carousel.size(); i++) {
                decrements.add(1); // Inicializar todos los valores de decremento a 1
            }
        }

        @Override
        public int next() {
            if (isFinished()) {
                return -1;
            }

            while (carousel.get(currentIndex) == 0) {
                currentIndex = (currentIndex + 1) % carousel.size();
                if (isFinished()) {
                    return -1;
                }
            }

            int value = carousel.get(currentIndex);
            int decrement = decrements.get(currentIndex);

            int returnValue = value;

            value -= decrement;
            decrements.set(currentIndex, decrement + 1);

            if (value > 0) {
                carousel.set(currentIndex, value);
            } else {
                carousel.set(currentIndex, 0);
            }

            currentIndex = (currentIndex + 1) % carousel.size();
            return returnValue;
        }
    }
}