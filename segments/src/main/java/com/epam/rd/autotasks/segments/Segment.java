package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {

    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {
        this.start = start;
        this.end = end;

        if ((start == null || end == null) || start.equals(end) ||(start.getX() == end.getX() && start.getY() == end.getY())) {
            throw new RuntimeException();
        }
    }

    double length() {
        double x = abs(start.getX() - end.getX());
        double y = abs(start.getY() - end.getY());
        double result = sqrt(pow(x, 2) + pow(y, 2));

        return result;


    }

    Point middle() {
        double x = ((start.getX() + end.getX()) / 2);
        double y = ((start.getY() + end.getY()) / 2);
        Point result = new Point(x, y);

        return result;     
        

    }

    Point intersection(Segment another) {
        double x1 = start.getX();
        double y1 = start.getY();
        double x2 = end.getX();
        double y2 = end.getY();
        double x3 = another.start.getX();
        double y3 = another.start.getY();
        double x4 = another.end.getX();
        double y4 = another.end.getY();

        double a1 = y2 - y1;
        double b1 = x1 - x2;
        double c1 = x2 * y1 - x1 * y2;
        double a2 = y4 - y3;
        double b2 = x3 - x4;
        double c2 = x4 * y3 - x3 * y4;

        double d = a1 * b2 - a2 * b1;
        if (d == 0) {
            return null;
        }

        double x = (b1 * c2 - b2 * c1) / d;
        double y = (a2 * c1 - a1 * c2) / d;

        if (x < Math.min(x1, x2) || x > Math.max(x1, x2) || x < Math.min(x3, x4) || x > Math.max(x3, x4)) {
            return null;
        }

        if (y < Math.min(y1, y2) || y > Math.max(y1, y2) || y < Math.min(y3, y4) || y > Math.max(y3, y4)) {
            return null;
        }

        return new Point(x, y);
    }

}