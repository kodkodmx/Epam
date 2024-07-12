package com.epam.rd.autotasks.triangle;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Triangle {

    Point a;
    Point b;
    Point c;

    public Triangle(Point a, Point b, Point c) {
        //TODO

        this.a = a;
        this.b = b;
        this.c = c;

        if ((Triangle.length(a,b) < (Triangle.length(b,c) + Triangle.length(a,c))) 
        && (Triangle.length(b,c) < (Triangle.length(a,b) + Triangle.length(a,c))) 
        && (Triangle.length(a,c) < (Triangle.length(b,c) + Triangle.length(a,b)))) {
        //System.out.println("Triangle is valid");

        } else {
            throw new IllegalArgumentException();
        }
    }

    public static double length(Point start, Point end) {
        double x = abs(start.getX() - end.getX());
        double y = abs(start.getY() - end.getY());
        double result = sqrt(pow(x, 2) + pow(y, 2));

        return result;


    }

    public double area() {
        //TODO
        double s = (Triangle.length(a,b) + Triangle.length(b,c) + Triangle.length(a,c)) / 2;
        double area = sqrt(s * (s - Triangle.length(a,b)) * (s - Triangle.length(b,c)) * (s - Triangle.length(a,c)));

        return area;
    }

    public Point centroid(){
        //TODO
        Point centroid = new Point((a.getX() + b.getX() + c.getX()) / 3, (a.getY() + b.getY() + c.getY()) / 3);
        return centroid;
    }

}
