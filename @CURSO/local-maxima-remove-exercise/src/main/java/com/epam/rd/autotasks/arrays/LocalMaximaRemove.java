package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = new int[]{18, 1, 3, 6, 7, -5}; //{1, 3, 6, -5}
            
        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array){
    
        //put your code here
    
        int[] result = new int[array.length];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            //System.out.println("i " + i + " j " + j);
            if ((i == 0) && (array[i] <= array[i + 1])) {
                result[j] = array[i];
                j++;
                //System.out.println("imprime 0 mas chico");

            }else if ((i == array.length - 1) && (array[i] <= array[i - 1])) {
                result[j] = array[i];
                j++;
                //System.out.println("imprime ultimo mas chico");
            }else if ((i > 0) && (i < array.length - 1)) {
                if ((array[i] <= array[i - 1]) || (array[i] <= array[i + 1])) {
                result[j] = array[i];
                j++;
                //System.out.println("los de mas");
                //System.err.println("i " + i + " j " + j);
                }
            }
             
        } 


// aqui----------------------------------------------------------------



        /*for (int i = 0; i < array.length; i++) {
            // Check for local maxima
            if ((i == 0 && array[i] > array[i + 1]) || 
                (i == array.length - 1 && array[i] > array[i - 1]) ||
                (i > 0 && i < array.length - 1 && array[i] > array[i - 1] && array[i] > array[i + 1])) {
                continue; // Skip the local maxima
            }
            // Add the non-maxima element to the result array
            result[j] = array[i];
            j++;
        }*/


        return Arrays.copyOf(result, j);
    }
}

