package com.epam.rd.autotasks.validations;

public class ColorCodeValidation {
    public static boolean validateColorCode(String color) {
        if (color == null) {
            return false;
        }
        
        String regex = "^#([0-9a-fA-F]{6}|[0-9a-fA-F]{3})$";
        
        return color.matches(regex);
    }
}
