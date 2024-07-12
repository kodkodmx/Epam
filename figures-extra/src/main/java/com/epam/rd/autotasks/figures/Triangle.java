package com.epam.rd.autotasks.figures;

class Triangle extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;

    Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (((a == null || b == null || c == null)) || ((b.getX() - a.getX()) * (c.getY() - a.getY()) == (c.getX() - a.getX()) * (b.getY() - a.getY()))) {
            throw new IllegalArgumentException("Degenerative triangle");
        }
    }

    @Override
    public Point centroid() {
        return new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        return figure instanceof Triangle && this.a.equals(((Triangle) figure).a) && this.b.equals(((Triangle) figure).b) && this.c.equals(((Triangle) figure).c);
    }
}
