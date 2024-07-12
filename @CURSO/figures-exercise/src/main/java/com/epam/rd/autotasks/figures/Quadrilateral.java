package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double area() {
        double ab = Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + Math.pow(b.getY() - a.getY(), 2));
        double bc = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
        double cd = Math.sqrt(Math.pow(d.getX() - c.getX(), 2) + Math.pow(d.getY() - c.getY(), 2));
        double da = Math.sqrt(Math.pow(a.getX() - d.getX(), 2) + Math.pow(a.getY() - d.getY(), 2));
        double ac = Math.sqrt(Math.pow(c.getX() - a.getX(), 2) + Math.pow(c.getY() - a.getY(), 2));
        double bd = Math.sqrt(Math.pow(d.getX() - b.getX(), 2) + Math.pow(d.getY() - b.getY(), 2));
        double p = (ab + bc + ac) / 2;
        double p1 = (cd + da + ac) / 2;
        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ac)) + Math.sqrt(p1 * (p1 - cd) * (p1 - da) * (p1 - ac));
    }

    @Override
    public String toString() {
        return "Quadrilateral[" + "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" + "(" + d.getX() + "," + d.getY() + ")" + ']';
    }

    @Override
    public String pointsToString() {
        return "(" + a.getX() + "," + a.getY() + ")" + "(" + b.getX() + "," + b.getY() + ")" + "(" + c.getX() + "," + c.getY() + ")" + "(" + d.getX() + "," + d.getY() + ")";
    }

    @Override
    public Point leftmostPoint() {
        return a.getX() < b.getX() ? (a.getX() < c.getX() ? (a.getX() < d.getX() ? a : d) : (c.getX() < d.getX() ? c : d)) : (b.getX() < c.getX() ? (b.getX() < d.getX() ? b : d) : (c.getX() < d.getX() ? c : d));
    }

}
