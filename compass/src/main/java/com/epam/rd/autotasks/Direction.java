package com.epam.rd.autotasks;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private final int degrees;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    public static Direction ofDegrees(int degrees) {
        if (degrees < 0) {
            degrees = degrees + ((-degrees / 360) + 1) * 360;
        } else if (degrees >= 360) {
            degrees = degrees - (degrees / 360) * 360;
        }
        for (Direction direction : values()) {
            if (direction.degrees == degrees) {
                return direction;
            }
        }
        return null;
    }

    public static Direction closestToDegrees(int degrees) {
        if (degrees < 0) {
            degrees = degrees + ((-degrees / 360) + 1) * 360;
        } else if (degrees >= 360) {
            degrees = degrees - (degrees / 360) * 360;
        }
        
        Direction closest = null;
        int minDifference = Integer.MAX_VALUE;
    
        for (Direction direction : values()) {
            int directDifference = Math.abs(direction.degrees - degrees);
            int complementDifference = 360 - directDifference;
            int smallerDiff = Math.min(directDifference, complementDifference);
            
            if (smallerDiff < minDifference) {
                minDifference = smallerDiff;
                closest = direction;
            }

        }
    
        return closest;
    }
    

    public Direction opposite() {
        int oppositeDegrees = (this.degrees + 180) % 360;
        return ofDegrees(oppositeDegrees);
    }

    public int differenceDegreesTo(Direction direction) {
        int difference = Math.abs(this.degrees - direction.degrees);
        return Math.min(difference, 360 - difference);
    }
}
