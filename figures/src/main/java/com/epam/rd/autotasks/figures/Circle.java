package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final double radius;
    private final Point center;

    public Circle(Point center, double radius) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle[" + "(" + center.getX() + "," + center.getY() + ")" + radius + "]";
    }

    @Override
    public String pointsToString() {
        return "(" + center.getX() + "," + center.getY() + ")";
    }

    @Override
    public Point leftmostPoint() {
        return new Point(center.getX() - radius, center.getY());
    }
}
