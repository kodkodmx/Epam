package com.epam.rd.autotasks.validations;

import java.util.regex.Pattern;

public class EpamEmailValidation {

    private static final Pattern EPAM_EMAIL_PATTERN = Pattern.compile("^[a-zA-Z]+_[a-zA-Z]+(\\d+)?@epam\\.com$");

    public static boolean validateEpamEmail(String email) {
        if (email == null) {
            return false;
        }
        return EPAM_EMAIL_PATTERN.matcher(email).matches();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(validateEpamEmail("william_shakespeare@epam.com")); // true
        System.out.println(validateEpamEmail("lu_e@epam.com")); // true
        System.out.println(validateEpamEmail("william_shakespeare1@epam.com")); // true
        System.out.println(validateEpamEmail("william_shakespeare2@epam.com")); // true
        System.out.println(validateEpamEmail("william@epam.com")); // false
        System.out.println(validateEpamEmail("william.shakespeare@epam.com")); // false
        System.out.println(validateEpamEmail("william...shakespeare@epam.com")); // false
        System.out.println(validateEpamEmail("william-shakespeare@epam.com")); // false
        System.out.println(validateEpamEmail("shakespeare123@epam.com")); // false
        System.out.println(validateEpamEmail("william_$hakespeare@epam.com")); // false
    }
}


