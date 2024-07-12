package com.epam.rd.autotasks.figures;

import java.math.BigDecimal;
import java.math.RoundingMode;

class Circle extends Figure {
    private final Point center;
    private final double radius;

    Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        if (radius <= 0 || Double.isNaN(radius)) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        if (center == null) {
            throw new IllegalArgumentException("Center must not be null");
        }
    }

    public BigDecimal round(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    //Math.abs(expected - actual) <= epsilon

    public boolean isTheSame(Figure figure) {
        double tolerance = 0.0001;
        return figure instanceof Circle && Math.abs(this.centroid().getX() - figure.centroid().getX()) <= tolerance && Math.abs(this.centroid().getY() - figure.centroid().getY()) <= tolerance && Math.abs(this.radius - ((Circle) figure).radius) <= tolerance;
    }
}
