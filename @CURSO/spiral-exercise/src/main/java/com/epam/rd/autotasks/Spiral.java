package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        
        int iniciofilas = 0;
        int finfilas = rows;
        int iniciocolumnas = 0;
        int fincolumnas = columns;
        int[][] spiral = new int[rows][columns];
        int valor = 1;

        while (iniciofilas < finfilas && iniciocolumnas < fincolumnas) {            
      
            //top row
            for (int i = iniciocolumnas; i < fincolumnas; i++) {
                spiral[iniciofilas][i] = valor++;
                                             
            }
            iniciofilas = iniciofilas + 1;

            //right column
            for (int j = iniciofilas; j < finfilas; j++) {
                spiral[j][fincolumnas - 1] = valor++;
            }
            fincolumnas = fincolumnas - 1;

            //bottom row
            if (iniciofilas < finfilas) {
                for (int i = fincolumnas - 1; i >= iniciocolumnas; i--) {
                    spiral[finfilas - 1][i] = valor++;
                }
                finfilas = finfilas - 1;
            }

            //left column
            if (iniciocolumnas < fincolumnas) {
                for (int i = finfilas - 1; i >= iniciofilas; i--) {
                    spiral[i][iniciocolumnas] = valor++;
                }
                iniciocolumnas = iniciocolumnas + 1;
            }

        }
    System.out.println("");
    return spiral;
    }
}