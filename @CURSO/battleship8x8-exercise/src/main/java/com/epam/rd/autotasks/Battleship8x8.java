package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = invertBits(ships);
    }

    public boolean shoot(String shot) {
        int col = shot.charAt(0) - 'A';
        int row = shot.charAt(1) - '1';
        Long mask = 1L << (row * 8 + col);

        boolean hit = (ships & mask) != 0;
        shots |= mask;
        return hit;
    }

    public String state() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Long mask = 1L << (row * 8 + col);
                boolean isShot = (shots & mask) != 0;
                boolean isShip = (ships & mask) != 0;

                if (isShot) {
                    sb.append(isShip ? '☒' : '×');
                } else {
                    sb.append(isShip ? '☐' : '.');
                }
            }
            sb.append('\n');
        }
        return sb.toString().strip();
    }

    private long invertBits(Long value) {
        long result = 0L;
        for (int i = 0; i < 64; i++) {
            if ((value & (1L << i)) != 0) {
                result |= (1L << (63 - i));
            }
        }
        return result;
    }
}