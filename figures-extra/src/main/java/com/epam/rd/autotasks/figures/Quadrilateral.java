package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;
    private static final double EPSILON = 1e-10;

    Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null) {
            throw new IllegalArgumentException("Points cannot be null");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;

        if (!isConvex() || area() < EPSILON) {
            throw new IllegalArgumentException("Invalid quadrilateral");
        }
    }

    private double area() {
        return Math.abs(
            a.getX() * b.getY() + b.getX() * c.getY() + c.getX() * d.getY() + d.getX() * a.getY()
            - a.getY() * b.getX() - b.getY() * c.getX() - c.getY() * d.getX() - d.getY() * a.getX()
        ) / 2.0;
    }

    private boolean isConvex() {
        return crossProductSign(a, b, c) * crossProductSign(b, c, d) > 0 &&
               crossProductSign(b, c, d) * crossProductSign(c, d, a) > 0 &&
               crossProductSign(c, d, a) * crossProductSign(d, a, b) > 0 &&
               crossProductSign(d, a, b) * crossProductSign(a, b, c) > 0;
    }

    private double crossProductSign(Point p1, Point p2, Point p3) {
        return (p2.getX() - p1.getX()) * (p3.getY() - p2.getY()) -
               (p2.getY() - p1.getY()) * (p3.getX() - p2.getX());
    }

    @Override
    public Point centroid() {
        Point centroid1 = triangleCentroid(a, b, c);
        Point centroid2 = triangleCentroid(a, c, d);
        double area1 = triangleArea(a, b, c);
        double area2 = triangleArea(a, c, d);

        double centroidX = (centroid1.getX() * area1 + centroid2.getX() * area2) / (area1 + area2);
        double centroidY = (centroid1.getY() * area1 + centroid2.getY() * area2) / (area1 + area2);
        return new Point(centroidX, centroidY);
    }

    private Point triangleCentroid(Point p1, Point p2, Point p3) {
        return new Point(
            (p1.getX() + p2.getX() + p3.getX()) / 3.0,
            (p1.getY() + p2.getY() + p3.getY()) / 3.0
        );
    }

    private double triangleArea(Point p1, Point p2, Point p3) {
        return Math.abs(
            p1.getX() * (p2.getY() - p3.getY()) +
            p2.getX() * (p3.getY() - p1.getY()) +
            p3.getX() * (p1.getY() - p2.getY())
        ) / 2.0;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Quadrilateral)) {
            return false;
        }
        Quadrilateral other = (Quadrilateral) figure;
        Point[] thisPoints = {a, b, c, d};
        Point[] otherPoints = {other.a, other.b, other.c, other.d};
        return arePointsEqual(thisPoints, otherPoints);
    }

    private boolean arePointsEqual(Point[] points1, Point[] points2) {
        for (Point p1 : points1) {
            boolean found = false;
            for (Point p2 : points2) {
                if (pointsAreEqual(p1, p2)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    private boolean pointsAreEqual(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) < EPSILON &&
               Math.abs(p1.getY() - p2.getY()) < EPSILON;
    }
}