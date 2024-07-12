package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double ab = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        double bc = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
        double ac = Math.sqrt(Math.pow(c.getX() - a.getX(), 2) + Math.pow(c.getY() - a.getY(), 2));
        double p = (ab + bc + ac) / 2;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
    }

    @Override
    public String toString() {
        return "Triangle[" + "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" +']';
    }

    @Override
    public String pointsToString() {
        return "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")";
    }

    @Override
    public Point leftmostPoint() {
        return a.getX() < b.getX() ? (a.getX() < c.getX() ? a : c) : (b.getX() < c.getX() ? b : c);
    }

}
